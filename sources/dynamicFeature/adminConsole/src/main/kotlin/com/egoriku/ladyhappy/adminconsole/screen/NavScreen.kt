package com.egoriku.ladyhappy.adminconsole.screen

sealed class NavScreen(val route: String) {
    object ConsoleScreen : NavScreen("console")
    object PublishNewsScreen : NavScreen("publishNews")
    object PublishProductScreen : NavScreen("publishProduct")
    object ManageUsersScreen : NavScreen("manageUsers")
}