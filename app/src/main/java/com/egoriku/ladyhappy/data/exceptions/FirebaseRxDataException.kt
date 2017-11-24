package com.egoriku.ladyhappy.data.exceptions

import android.support.annotation.NonNull
import com.google.firebase.database.DatabaseError

open class FirebaseRxDataException(@NonNull error: DatabaseError) : Exception() {

    var error: DatabaseError
        protected set

    init {
        this.error = error
    }

    override fun toString(): String {
        return "RxFirebaseDataException{error=$error}"
    }
}