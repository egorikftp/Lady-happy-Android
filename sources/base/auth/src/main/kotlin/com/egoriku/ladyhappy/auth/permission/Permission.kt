package com.egoriku.ladyhappy.auth.permission

sealed class Permission {

    abstract val name: String

    object User : Permission() {
        override val name: String = "user"
    }

    object Admin : Permission() {
        override val name: String = "admin"
    }
}