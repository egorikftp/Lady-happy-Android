package com.egoriku.ladyhappy.beagle

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.egoriku.extensions.listeners.SimpleActivityLifecycleCallbacks
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.koin.AccountProvider
import com.egoriku.ladyhappy.login.presentation.LoginFragment
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagleCore.configuration.Trick
import org.koin.core.KoinComponent
import org.koin.core.inject

private const val ID_ACCOUNTS = "accounts"

class BeagleLifecycleListener : SimpleActivityLifecycleCallbacks(), KoinComponent {

    private val accountProvider: AccountProvider by inject()

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
            if (fragment is LoginFragment) {
                val view = fragment.view

                Beagle.learn(
                        trick = Trick.SimpleList(
                                id = ID_ACCOUNTS,
                                title = "Test accounts",
                                items = accountProvider.accounts,
                                isInitiallyExpanded = true,
                                onItemSelected = { account ->
                                    view?.findViewById<EditText>(R.id.loginEmail)?.setText(account.name)
                                    view?.findViewById<EditText>(R.id.loginPassword)?.setText(account.password)
                                    Beagle.dismiss()
                                }
                        )
                )
            }
        }

        override fun onFragmentPaused(fm: FragmentManager, fragment: Fragment) {
            if (fragment is LoginFragment) {
                Beagle.forget(ID_ACCOUNTS)
            }
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
        }
    }
}