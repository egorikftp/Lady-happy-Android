package com.egoriku.ladyhappy.navigation.navigator.platform

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.egoriku.ladyhappy.navigation.command.Add
import com.egoriku.ladyhappy.navigation.command.Back
import com.egoriku.ladyhappy.navigation.command.Command
import com.egoriku.ladyhappy.navigation.command.Replace
import com.egoriku.ladyhappy.navigation.navigator.INavigator
import com.egoriku.ladyhappy.navigation.screen.ActivityScreen
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.egoriku.ladyhappy.navigation.screen.Screen

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
            is FragmentScreen -> addFragment(screen, command.containerId)
        }
    }

    private fun processBack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }

    private fun addFragment(screen: FragmentScreen, containerId: Int) {
        if (containerId != 0) {
            fragmentManager
                    .beginTransaction()
                    .add(containerId, screen.fragment, screen.fragment.javaClass.name)
                    .addToBackStack(screen.fragment.javaClass.name)
                    .commit()
        } else {
            fragmentManager
                    .beginTransaction()
                    .add(this.containerId, screen.fragment, screen.fragment.javaClass.name)
                    .addToBackStack(screen.fragment.javaClass.name)
                    .commit()
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
}