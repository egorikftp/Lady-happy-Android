package com.egoriku.core

import com.google.firebase.firestore.FirebaseFirestore

interface IFirebaseFirestore {

    fun getFirebase(): FirebaseFirestore
}
