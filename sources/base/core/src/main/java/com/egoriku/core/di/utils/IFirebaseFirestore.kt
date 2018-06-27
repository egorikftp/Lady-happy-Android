package com.egoriku.core.di.utils

import com.google.firebase.firestore.FirebaseFirestore

interface IFirebaseFirestore {

    fun getFirebase(): FirebaseFirestore
}
