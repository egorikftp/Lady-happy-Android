package com.egoriku.ladyhappy.firebase

import com.egoriku.ladyhappy.data.entities.CategoriesDocumentEntity
import com.egoriku.ladyhappy.data.entities.CategoryEntity
import com.egoriku.ladyhappy.data.entities.NewsDocumentEntity
import com.egoriku.ladyhappy.data.entities.NewsEntity
import com.egoriku.ladyhappy.data.exceptions.FirebaseRxDataException
import com.google.firebase.firestore.Query
import io.reactivex.Observable

class RxFirestore {

    companion object {
        fun getObservableCategories(query: Query, model: CategoriesDocumentEntity, clazz: Class<CategoryEntity>): Observable<CategoriesDocumentEntity> =
                Observable.create { emitter ->
                    query.addSnapshotListener { querySnapshot, exception ->
                        if (exception != null) {
                            if (!emitter.isDisposed) {
                                emitter.onError(FirebaseRxDataException(exception.message, exception.cause))
                            }
                        } else {
                            querySnapshot.documents.mapTo(model.categories) {
                                it.toObject(clazz)
                            }

                            if (!emitter.isDisposed) {
                                emitter.onNext(model)
                            }
                        }
                    }
                }

        fun getObservableNews(query: Query, model: NewsDocumentEntity, clazz: Class<NewsEntity>): Observable<NewsDocumentEntity> =
                Observable.create { emitter ->
                    query.addSnapshotListener { querySnapshot, exception ->
                        when (exception) {
                            null -> {
                                model.news = querySnapshot.documents.associateBy(
                                        keySelector = { document -> document.id },
                                        valueTransform = { snapshot -> snapshot.toObject(clazz) })

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