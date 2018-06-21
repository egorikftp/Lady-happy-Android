package com.egoriku.core.di

import com.google.firebase.firestore.FirebaseFirestore

interface IFirebaseFirestore {

    fun getFirebase(): FirebaseFirestore
}
