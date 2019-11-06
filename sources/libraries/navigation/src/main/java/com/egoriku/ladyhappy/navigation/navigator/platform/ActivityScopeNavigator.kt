package com.egoriku.ladyhappy.navigation.navigator.platform

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.egoriku.ladyhappy.navigation.command.*
import com.egoriku.ladyhappy.navigation.navigator.INavigator
import com.egoriku.ladyhappy.navigation.screen.ActivityScreen
import com.egoriku.ladyhappy.navigation.screen.DialogFragmentScreen
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class ActivityScopeNavigator(
        private val activity: FragmentActivity,
        private val containerId: Int,
        private val fragmentManager: FragmentManager = activity.supportFragmentManager
) : INavigator {

    override fun applyCommands(commands: Array<out Command>) {
        commands.forEach {
            applyCommand(it)
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Replace -> processReplace(command)
            is Add -> processAdd(command)
            is Back -> processBack()
        }
    }

    private fun processReplace(command: Replace) {
        when (val screen = command.screen) {
            is FragmentScreen -> replaceFragment(screen)
            is ActivityScreen -> replaceActivity(screen)
        }
    }

    private fun processAdd(command: Add) {
        when (val screen = command.screen) {
            is FragmentScreen -> TODO()
            is ActivityScreen -> TODO()
            is DialogFragmentScreen -> showDialogFragment(screen)
        }
    }

    private fun processBack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }

    private fun replaceFragment(screen: FragmentScreen) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, screen.fragment)
                .commit()
    }

    private fun replaceActivity(screen: ActivityScreen) {
        activity.startActivity(screen.intent)
        activity.finish()
    }

    private fun showDialogFragment(screen: DialogFragmentScreen) = with(screen) {
        dialogFragment.show(fragmentManager, tag)
    }
}