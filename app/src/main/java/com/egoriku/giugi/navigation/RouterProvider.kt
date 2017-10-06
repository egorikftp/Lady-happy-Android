package com.egoriku.giugi.navigation

import ru.terrakok.cicerone.Router

interface RouterProvider {

    fun getNavigationRouter(): Router?
}
