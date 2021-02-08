package com.egoriku.ladyhappy.detailpage.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.constant.CollectionPath.ALL_HATS
import com.egoriku.ladyhappy.detailpage.data.entity.DetailEntity
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class DetailDataSource(private val firebase: IFirebase) : PagingSource<QuerySnapshot, DetailEntity>() {

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, DetailEntity> {
        return runCatching {
            val currentPage = params.key ?: firebase.firebaseFirestore
                    .collection(ALL_HATS)
                    .orderBy("id")
                    .limit(PAGE_SIZE.toLong())
                    .get()
                    .await()

            delay(500)

            return if (currentPage.documents.isEmpty()) {
                LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                )
            } else {
                val snapshotOffset = currentPage.documents[currentPage.size() - 1]

                val nextPage = firebase.firebaseFirestore
                        .collection(ALL_HATS)
                        .limit(PAGE_SIZE.toLong())
                        .orderBy("id")
                        .startAfter(snapshotOffset)
                        .get()
                        .await()

                LoadResult.Page(
                        data = currentPage.toObjects(),
                        prevKey = null,
                        nextKey = nextPage
                )
            }
        }.getOrElse {
            LoadResult.Error(it)
        }
    }

    override fun getRefreshKey(state: PagingState<QuerySnapshot, DetailEntity>): QuerySnapshot? = null
}