package com.egoriku.ladyhappy.tools

import com.egoriku.ladyhappy.core.IFirebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

internal class Firebase : IFirebase {

    override val firebaseFirestore: FirebaseFirestore by lazy {
        Firebase.firestore.apply {
            firestoreSettings {
                isPersistenceEnabled = true
            }
        }
    }

    override val firebaseStorage: FirebaseStorage by lazy {
        Firebase.storage
    }
}
