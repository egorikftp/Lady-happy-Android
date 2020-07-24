package com.egoriku.ladyhappy.tools

import com.egoriku.core.IFirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

internal class FirebaseFirestore : IFirebaseFirestore {

    private val firebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    init {
        firebaseFirestore.apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
        }
    }

    override fun getFirebase(): FirebaseFirestore = firebaseFirestore
}
