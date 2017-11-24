package com.egoriku.ladyhappy.navigation

import ru.terrakok.cicerone.Router

interface RouterProvider {

    fun getNavigationRouter(): Router?
}
