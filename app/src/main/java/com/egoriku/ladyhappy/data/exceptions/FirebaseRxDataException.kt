package com.egoriku.ladyhappy.data.exceptions

open class FirebaseRxDataException(private val errorMessage: String?, private val throwable: Throwable?) : Exception() {

    override fun toString() = "RxFirebaseDataException{message=$errorMessage, throwable=$throwable}"
}