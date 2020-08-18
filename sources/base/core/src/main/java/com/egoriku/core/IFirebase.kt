package com.egoriku.core

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface IFirebase {

    val firebaseFirestore: FirebaseFirestore

    val firebaseStorage: FirebaseStorage
}
