package com.egoriku.network.firestore

import com.egoriku.core.exception.NoSuchDocumentException
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import io.reactivex.*

/**
 * Listens to changes at the given [DocumentReference] (receiver) and returns an [Observable] that
 * emits an item whenever there is a new [DocumentSnapshot].
 * The listener is removed when the [Observable]'s subscription is disposed.
 * The type needs to have a constructor that takes no argument in order to call [DocumentSnapshot.toObject].
 * <p>
 * @receiver [DocumentReference] to listen to
 * <p>
 * @throws [NoSuchDocumentException] if the document doesn't exist
 */
inline fun <reified T> DocumentReference.getObservable(): Observable<T> {
    return Observable.create { emitter ->
        val listener = addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            documentSnapshot?.let {
                if (documentSnapshot.exists()) {
                    try {
                        emitter.onNext(documentSnapshot.toObject(T::class.java)!!)
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                } else {
                    emitter.onError(NoSuchDocumentException())
                }
            }
            firebaseFirestoreException?.let { emitter.onError(it) }
        }
        emitter.setCancellable { listener.remove() }
    }
}

/**
 * Listens to changes at the given [DocumentReference] (receiver) and returns a [Flowable] that
 * emits an item whenever there is a new [DocumentSnapshot].
 * The listener is removed when the [Flowable]'s subscription is disposed.
 * The type needs to have a constructor that takes no argument in order to call [DocumentSnapshot.toObject].
 * <p>
 * @receiver [DocumentReference] to listen to
 * <p>
 * @throws [NoSuchDocumentException] if the document doesn't exist
 */
inline fun <reified T> DocumentReference.getFlowable(backpressureStrategy: BackpressureStrategy): Flowable<T> {
    return Flowable.create({ emitter ->
        val listener = addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            documentSnapshot?.let {
                if (documentSnapshot.exists()) {
                    try {
                        emitter.onNext(documentSnapshot.toObject(T::class.java)!!)
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                } else {
                    emitter.onError(NoSuchDocumentException())
                }
            }
            firebaseFirestoreException?.let { emitter.onError(it) }
        }
        emitter.setCancellable { listener.remove() }
    }, backpressureStrategy)
}

/**
 * Gets the value at the given [DocumentReference] (receiver) once and returns a [Single] that
 * emits an item if it exists or calls onError.
 * The type needs to have a constructor that takes no argument in order to call [DocumentSnapshot.toObject].
 * <p>
 * @receiver [DocumentReference] to listen to
 * <p>
 * @throws [NoSuchDocumentException] if the document doesn't exist
 */
inline fun <reified T> DocumentReference.getSingle(): Single<T> {
    return Single.create { emitter ->
        get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        try {
                            emitter.onSuccess(it.toObject(T::class.java)!!)
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    } else {
                        emitter.onError(NoSuchDocumentException())
                    }
                }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Listens to changes at the given [CollectionReference] (receiver) and returns an [Observable] that
 * emits a list of items whenever there is a new [QuerySnapshot].
 * The listener is removed when the [Observable]'s subscription is disposed.
 * The type needs to have a constructor that takes no argument in order to call [QuerySnapshot.toObjects]
 * <p>
 * @receiver [CollectionReference] to listen to
 * <p>
 */
inline fun <reified T> CollectionReference.getObservable(): Observable<List<T>> {
    return Observable.create { emitter ->
        val listener = addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            querySnapshot?.let {
                try {
                    emitter.onNext(it.toObjects(T::class.java))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
            firebaseFirestoreException?.let { emitter.onError(it) }
        }
        emitter.setCancellable { listener.remove() }
    }
}

/**
 * Listens to changes at the given [CollectionReference] (receiver) and returns an [Flowable] that
 * emits a list of items whenever there is a new [QuerySnapshot].
 * The listener is removed when the [Flowable]'s subscription is disposed.
 * The type needs to have a constructor that takes no argument in order to call [QuerySnapshot.toObjects]
 * <p>
 * @receiver [CollectionReference] to listen to
 * @param backpressureStrategy to use
 * <p>
 */
inline fun <reified T> CollectionReference.getFlowable(backpressureStrategy: BackpressureStrategy): Flowable<List<T>> {
    return Flowable.create({ emitter ->
        val listener = addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            querySnapshot?.let {
                try {
                    emitter.onNext(it.toObjects(T::class.java))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
            firebaseFirestoreException?.let { emitter.onError(it) }
        }
        emitter.setCancellable { listener.remove() }
    }, backpressureStrategy)
}

/**
 * Listens to changes for the given [Query] (receiver) and returns an [Observable] that
 * emits a list of items whenever there is a new [QuerySnapshot].
 * The listener is removed when the [Observable]'s subscription is disposed.
 * The type needs to have a constructor that takes no argument in order to call [QuerySnapshot.toObjects]
 * <p>
 * @receiver [Query] to listen to
 * <p>
 */
inline fun <reified T> Query.getObservable(): Observable<List<T>> {
    return Observable.create { emitter ->
        val listener = addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            querySnapshot?.let {
                try {
                    emitter.onNext(it.toObjects(T::class.java))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
            firebaseFirestoreException?.let { emitter.onError(it) }
        }
        emitter.setCancellable { listener.remove() }
    }
}

/**
 * Listens to changes for the given [Query] (receiver) and returns a [Flowable] that
 * emits a list of items whenever there is a new [QuerySnapshot].
 * The listener is removed when the [Flowable]'s subscription is disposed.
 * The type needs to have a constructor that takes no argument in order to call [QuerySnapshot.toObjects]
 * <p>
 * @receiver [Query] to listen to
 * @param backpressureStrategy to use
 * <p>
 */
inline fun <reified T> Query.getFlowable(backpressureStrategy: BackpressureStrategy): Flowable<List<T>> {
    return Flowable.create({ emitter ->
        val listener = addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            querySnapshot?.let {
                try {
                    emitter.onNext(it.toObjects(T::class.java))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
            firebaseFirestoreException?.let { emitter.onError(it) }
        }
        emitter.setCancellable { listener.remove() }
    }, backpressureStrategy)
}

/**
 * Gets the current value at the given [CollectionReference] (receiver) and returns a [Single] that
 * emits the list of items found at that [CollectionReference].
 * The type needs to have a constructor that takes no argument in order to call [QuerySnapshot.toObjects]
 * <p>
 * @receiver [CollectionReference] to listen to
 * <p>
 */
inline fun <reified T> CollectionReference.getSingle(): Single<List<T>> {
    return Single.create { emitter ->
        get()
                .addOnSuccessListener {
                    try {
                        emitter.onSuccess(it.toObjects(T::class.java))
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Gets the current value for the given [Query] (receiver) and returns a [Single] that
 * emits the list of items found for that [Query].
 * The type needs to have a constructor that takes no argument in order to call [QuerySnapshot.toObjects].
 * <p>
 * @receiver [Query] to listen to
 * <p>
 */
inline fun <reified T> Query.getSingle(): Single<List<T>> {
    return Single.create { emitter ->
        get()
                .addOnSuccessListener {
                    try {
                        emitter.onSuccess(it.toObjects(T::class.java))
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Set's the parameter [item] at the given [DocumentReference] (receiver) and returns a
 * completable that completes when the transaction is complete or calls onError otherwise.
 *
 * @param item to be set at the [DocumentReference]
 * @receiver [DocumentReference] to set the item to
 */
fun <T : Any> DocumentReference.setDocument(item: T): Completable {
    return Completable.create { emitter ->
        set(item)
                .addOnCompleteListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Delete's the document at the given [DocumentReference] (receiver) and returns a
 * completable that completes when the transaction is complete or calls onError otherwise.
 *
 * @receiver [DocumentReference] to delete
 */
fun DocumentReference.deleteDocument(): Completable {
    return Completable.create { emitter ->
        delete()
                .addOnCompleteListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
    }
}

fun <T : Any> Task<T>.getCompletable(): Completable {
    return Completable.create { emitter ->
        addOnSuccessListener { emitter.onComplete() }
        addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Adds the given [item] to the collection at the [CollectionReference] (receiver) and returns a
 * [Single] that emits the [DocumentReference] for the added item or emits an error if the item
 * wasn't added to the collection.
 *
 * @receiver [CollectionReference] to add the item to
 */
fun <T : Any> CollectionReference.addDocumentSingle(item: T): Single<DocumentReference> {
    return Single.create { emitter ->
        add(item)
                .addOnSuccessListener { emitter.onSuccess(it) }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Updates the document at the given [DocumentReference] (receiver) with a field specified by
 * [field] and the the new value of [newValue] and returns a completable which completes if the
 * operation is successful or calls onError otherwise.
 *
 * @param [field] to update - [String] name of the field in Firestore
 * @param [newValue] updated value of any of the types supported by Firestore
 */
fun DocumentReference.updateDocumentCompletable(field: String, newValue: Any): Completable {
    return Completable.create { emitter ->
        update(field, newValue)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Updates the document at the given [DocumentReference] (receiver) with a set of new values specified
 * in a map with the fields (Strings of names of the fields to be updated) and the new values of any type
 * supported by firestore as the map's value. Returns a completable which completes if the
 * operation is successful or calls onError otherwise.
 *
 * @param updatedValues [Map] of field names (keys) and updated values (values)
 */
fun DocumentReference.updateDocumentCompletable(updatedValues: Map<String, Any>): Completable {
    return Completable.create { emitter ->
        update(updatedValues)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Runs a [Transaction] specified by [transaction] and returns a single that emits a value of [ReturnType]
 * if the transaction completes successfully or calls onError otherwise.
 * User [Void] as a return type if no value should be returned.
 *
 * @param transaction to be run
 */
fun <ReturnType : Any> FirebaseFirestore.runTransactionSingle(transaction: Transaction.Function<ReturnType>): Single<ReturnType> {
    return Single.create { emitter ->
        runTransaction(transaction)
                .addOnSuccessListener { emitter.onSuccess(it) }
                .addOnFailureListener { emitter.onError(it) }
    }
}

/**
 * Commits the given [WriteBatch] (receiver) and returns a completable that completes if the operation
 * is successful or calls onError otherwise.
 *
 * @receiver [WriteBatch] to execute
 */
fun WriteBatch.getCompletable(): Completable {
    return Completable.create { emitter ->
        commit()
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
    }
}