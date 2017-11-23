package com.egoriku.giugi.firebase

import android.support.annotation.NonNull
import com.egoriku.giugi.data.entities.CategoriesEntity
import com.egoriku.giugi.data.entities.CategoryEntity
import com.google.firebase.firestore.Query
import io.reactivex.Observable

class RxFirestore {

    companion object {
        @NonNull
        fun <T> getObservable(@NonNull query: Query, @NonNull clazz: Class<T>): Observable<List<Class<T>>> {

            return Observable.create { emitter ->
                query.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        if (!emitter.isDisposed) {
                            TODO("implement new exception logic")
                            //emitter.onError(FirebaseRxDataException(firebaseFirestoreException.message))
                        }
                    } else {
                        val value = querySnapshot.toObjects(clazz::class.java).toList()

                        if (!emitter.isDisposed) {
                            emitter.onNext(value)
                        }
                    }
                }
            }
        }

        @NonNull
        fun getObservableCategories(query: Query, model: CategoriesEntity, pojoModel: Class<CategoryEntity>): Observable<CategoriesEntity> {
            return Observable.create { emitter ->
                query.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        if (!emitter.isDisposed) {
                            TODO("implement new exception logic")
                            //emitter.onError(FirebaseRxDataException(firebaseFirestoreException.message))
                        }
                    } else {
                        val categories = HashMap<String, CategoryEntity>()
                        for (document in querySnapshot.documents) {
                            categories.put(document.id, document.toObject(pojoModel))
                        }

                        model.categories = categories

                        if (!emitter.isDisposed) {
                            emitter.onNext(model)
                        }
                    }
                }
            }
        }
    }
}