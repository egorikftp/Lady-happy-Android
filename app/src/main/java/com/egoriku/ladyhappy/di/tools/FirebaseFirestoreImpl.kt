package com.egoriku.ladyhappy.di.tools

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FirebaseFirestoreImpl : IFirebaseFirestore {
    private val firebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    init {
        firebaseFirestore.apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .setTimestampsInSnapshotsEnabled(true)
                    .build()
        }
    }

    override fun getFirebase(): FirebaseFirestore = firebaseFirestore
}
