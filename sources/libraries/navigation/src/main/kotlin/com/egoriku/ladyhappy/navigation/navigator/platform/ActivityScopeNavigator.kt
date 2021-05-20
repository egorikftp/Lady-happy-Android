package com.egoriku.ladyhappy.navigation.navigator.platform

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.egoriku.ladyhappy.navigation.command.Command
import com.egoriku.ladyhappy.navigation.command.Command.Add
import com.egoriku.ladyhappy.navigation.command.Command.Replace
import com.egoriku.ladyhappy.navigation.command.NavigationType
import com.egoriku.ladyhappy.navigation.navigator.INavigator
import com.egoriku.ladyhappy.navigation.navigator.extension.addSharedElements
import com.egoriku.ladyhappy.navigation.navigator.extension.addToBackStackIf
import com.egoriku.ladyhappy.navigation.screen.ActivityScreen
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class ActivityScopeNavigator(
    private val activity: FragmentActivity,
    private val containerId: Int,
    private val fullScreenContainerId: Int,
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
            is Command.Back -> processBack()
        }
    }

    private fun processReplace(command: Replace) {
        when (val screen = command.screen) {
            is FragmentScreen -> replaceFragment(screen = screen, command = command)
            is ActivityScreen -> replaceActivity(screen)
        }
    }

    private fun processAdd(command: Add) {
        when (val screen = command.screen) {
            is FragmentScreen -> addFragment(screen, command)
        }
    }

    private fun processBack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }

    private fun addFragment(screen: FragmentScreen, command: Add) {
        val id = when (command.navigationType) {
            NavigationType.DEFAULT -> containerId
            NavigationType.FULLSCREEN -> fullScreenContainerId
        }

        fragmentManager.commit {
            add(id, screen.fragment, screen.fragment.javaClass.name)
            addToBackStack(screen.fragment.javaClass.name)
        }
    }

    private fun replaceFragment(screen: FragmentScreen, command: Replace) {
        val id = when (command.navigationType) {
            NavigationType.DEFAULT -> containerId
            NavigationType.FULLSCREEN -> fullScreenContainerId
        }

        fragmentManager.commit {
            addSharedElements(command.sharedElements)
            replace(id, screen.fragment)
            addToBackStackIf(command.sharedElements.isNotEmpty()) {
                screen.fragment.javaClass.name
            }
        }
    }

    private fun replaceActivity(screen: ActivityScreen) {
        activity.startActivity(screen.intent)
        activity.finish()
    }
}