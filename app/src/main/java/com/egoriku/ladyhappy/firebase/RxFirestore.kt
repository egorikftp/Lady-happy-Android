package com.egoriku.ladyhappy.firebase

import com.egoriku.ladyhappy.data.entities.CategoriesDocumentEntity
import com.egoriku.ladyhappy.data.entities.CategoryEntity
import com.egoriku.ladyhappy.data.entities.NewsEntity
import com.egoriku.ladyhappy.data.entities.SingleNewsEntity
import com.egoriku.ladyhappy.data.exceptions.FirebaseRxDataException
import com.google.firebase.firestore.Query
import io.reactivex.Observable

class RxFirestore {

    companion object {
        fun getObservableCategories(query: Query, model: CategoriesDocumentEntity, clazz: Class<CategoryEntity>): Observable<CategoriesDocumentEntity> =
                Observable.create { emitter ->
                    query.addSnapshotListener { querySnapshot, exception ->

                        when (exception) {
                            null ->{
                                querySnapshot.documents.mapTo(model.categories) {
                                    it.toObject(clazz)
                                }

                                if (!emitter.isDisposed) {
                                    emitter.onNext(model)
                                }
                            }

                            else -> {
                                if (!emitter.isDisposed) {
                                    emitter.onError(FirebaseRxDataException(exception.message, exception.cause))
                                }
                            }
                        }
                    }
                }

        fun getObservableNews(query: Query, model: NewsEntity, clazz: Class<SingleNewsEntity>): Observable<NewsEntity> =
                Observable.create { emitter ->
                    query.addSnapshotListener { querySnapshot, exception ->
                        when (exception) {
                            null -> {
                                model.news.clear()

                                //Use logic for checking data change status

                                querySnapshot.documents.mapTo(model.news) {
                                    it.toObject(clazz)
                                }

                                if (!emitter.isDisposed) {
                                    emitter.onNext(model)
                                }
                            }

                            else -> {
                                if (!emitter.isDisposed) {
                                    emitter.onError(FirebaseRxDataException(exception.message, exception.cause))
                                }
                            }
                        }
                    }
                }
    }
}