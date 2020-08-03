package com.egoriku.ladyhappy.tools

import com.egoriku.core.IFirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

internal class FirebaseFirestore : IFirebaseFirestore {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    init {
        firestore.apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
        }
    }

    override fun getFirebase(): FirebaseFirestore = firestore
}
