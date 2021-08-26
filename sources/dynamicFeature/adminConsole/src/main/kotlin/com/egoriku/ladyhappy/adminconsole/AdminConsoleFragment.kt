package com.egoriku.ladyhappy.adminconsole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.adminconsole.screen.NavScreen
import com.egoriku.ladyhappy.adminconsole.screen.console.ConsoleScreen
import com.egoriku.ladyhappy.adminconsole.screen.manageusers.ManageUsersScreen
import com.egoriku.ladyhappy.adminconsole.screen.publishnews.PublishNewsScreen
import com.egoriku.ladyhappy.compose.ui.setThemeContent
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class AdminConsoleFragment : Fragment() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setThemeContent {
        Surface {
            val navController = rememberAnimatedNavController()

            AnimatedNavHost(
                navController = navController,
                startDestination = NavScreen.ConsoleScreen.route
            ) {
                composable(NavScreen.ConsoleScreen.route) {
                    ConsoleScreen(navController = navController)
                }
                composable(NavScreen.PublishNewsScreen.route) {
                    PublishNewsScreen()
                }
                composable(NavScreen.ManageUsersScreen.route) {
                    ManageUsersScreen()
                }
            }
        }
    }
}