package com.egoriku.ladyhappy.detailpage.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.key.CollectionPath.ALL_HATS
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.CATEGORY_ID
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.DATE
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.SUB_CATEGORY_ID
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.data.entity.DetailEntity
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await

class DetailDataSource(
        private val firebase: IFirebase,
        private val detailPageParams: DetailPageParams
) : PagingSource<QuerySnapshot, DetailEntity>() {

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, DetailEntity> {
        return runCatching {
            val currentPage = params.key ?: query().get().await()

            return if (currentPage.documents.isEmpty()) {
                LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                )
            } else {
                val snapshotOffset = currentPage.documents[currentPage.size() - 1]

                val nextPage = query()
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

    private fun query() = firebase.firebaseFirestore
            .collection(ALL_HATS)
            .whereEqualTo(CATEGORY_ID, detailPageParams.categoryId)
            .whereEqualTo(SUB_CATEGORY_ID, detailPageParams.subCategoryId)
            .orderBy(DATE, Query.Direction.DESCENDING)
            .limit(PAGE_SIZE.toLong())

    override fun getRefreshKey(state: PagingState<QuerySnapshot, DetailEntity>): QuerySnapshot? = null
}