package com.egoriku.ladyhappy.auth.permission

sealed class Permission {

    abstract val name: String

    object Anon : Permission() {
        override val name: String = "anon"
    }

    object Admin : Permission() {
        override val name: String = "admin"
    }

    object DemoMode : Permission() {
        override val name: String = "demo"
    }

    object User : Permission() {
        override val name: String = "user"
    }
}