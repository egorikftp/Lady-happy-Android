package com.egoriku.ladyhappy.firebase

import android.support.annotation.NonNull
import android.util.Log
import com.egoriku.ladyhappy.data.exceptions.FirebaseRxDataCastException
import com.egoriku.ladyhappy.data.exceptions.FirebaseRxDataException
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable

object RxFirebase {
    private val TAG = RxFirebase::class.java.name

    /**
     * As some Firebase [Task] are returning a Void (null) element and RxJava 2.X no longer
     * accept null values, we need to emit a different object
     * https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#nulls
     */
    class FirebaseTaskResponseSuccess

    @NonNull
    fun <T> getObservableForSingleEvent(@NonNull query: Query, @NonNull clazz: Class<T>): Observable<T> {
        return Observable.create { emitter ->
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, dataSnapshot.toString())
                    val value = dataSnapshot.getValue(clazz)
                    if (value != null) {
                        if (!emitter.isDisposed) {
                            emitter.onNext(value)
                        }
                    } else {
                        if (!emitter.isDisposed) {
                            emitter.onError(FirebaseRxDataCastException("Unable to cast Firebase data response to " + clazz.simpleName))
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    if (!emitter.isDisposed) {
                        emitter.onError(FirebaseRxDataException(error))
                    }
                }
            })
        }
    }

    @NonNull
    fun <T> getObservable(@NonNull query: Query, @NonNull clazz: Class<T>): Observable<T> {
        return Observable.create { emitter ->
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, dataSnapshot.toString())
                    val value = dataSnapshot.getValue(clazz)
                    if (value != null) {
                        if (!emitter.isDisposed) {
                            emitter.onNext(value)
                        }
                    } else {
                        query.removeEventListener(this)
                        if (!emitter.isDisposed) {
                            emitter.onError(FirebaseRxDataCastException("Unable to cast Firebase data response to " + clazz.simpleName))
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    query.removeEventListener(this)
                    if (!emitter.isDisposed) {
                        emitter.onError(FirebaseRxDataException(error))
                    }
                }
            })
        }
    }

    @NonNull
    fun <T> getObservable(@NonNull task: Task<T>): Observable<T> {
        return Observable.create { emitter ->
            task.addOnSuccessListener { result ->
                if (!emitter.isDisposed) {
                    emitter.onNext(result)
                }
            }.addOnFailureListener { e ->
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }

    /**
     * As RxJava 2 does not support Void (null) values as emitter item, we have to send an object back
     * @param task
     * @param objectToReturn
     * @param <T>
     * @return
    </T> */
    fun <T> getObservable(task: Task<T>, objectToReturn: Any): Observable<Any> {
        return Observable.create { emitter ->
            task.addOnSuccessListener {
                OnSuccessListener<Any> { result ->
                    if (!emitter.isDisposed) {
                        if (result is Void || result == null) {
                            emitter.onNext(objectToReturn)
                        } else {
                            emitter.onNext(result)
                        }
                    }
                }
            }.addOnFailureListener { e ->
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }
}