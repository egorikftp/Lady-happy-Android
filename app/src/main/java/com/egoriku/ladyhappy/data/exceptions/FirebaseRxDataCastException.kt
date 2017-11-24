package com.egoriku.ladyhappy.data.exceptions

class FirebaseRxDataCastException : Exception {

    constructor()

    constructor(detailMessage: String) : super(detailMessage)

    constructor(detailMessage: String, throwable: Throwable) : super(detailMessage, throwable)

    constructor(throwable: Throwable) : super(throwable)
}