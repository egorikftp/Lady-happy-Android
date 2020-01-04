package com.egoriku.ladyhappy.beagle

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.extensions.listeners.SimpleActivityLifecycleCallbacks
import com.egoriku.ladyhappy.extensions.logDm
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
            logDm("onFragmentResumed ${fragment::class.java.simpleName}")

            if (fragment is LoginFragment) {
                val view = fragment.view

                Beagle.learn(
                        trick = Trick.SimpleList(
                                id = ID_ACCOUNTS,
                                title = "Test accounts",
                                items = accountProvider.accounts,
                                isInitiallyExpanded = true,
                                onItemSelected = { account ->
                                    view?.findViewById<EditText>(R.id.login_email)?.setText(account.name)
                                    view?.findViewById<EditText>(R.id.login_password)?.setText(account.password)
                                    Beagle.dismiss(fragment.requireActivity())
                                }
                        )
                )
            }
        }

        override fun onFragmentPaused(fm: FragmentManager, fragment: Fragment) {
            logDm("onFragmentDestroyed ${fragment::class.java.simpleName}")

            if (fragment is LoginFragment) {
                Beagle.forget(ID_ACCOUNTS)
            }
        }
    }

    override fun onActivityResumed(activity: Activity) {
        logDm("onActivityCreated ${activity::class.java.simpleName}")

        if (activity.javaClass.name == "leakcanary.internal.activity.LeakActivity") {
            return
        }

        (activity as FragmentActivity)
                .supportFragmentManager
                .registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
    }

    override fun onActivityPaused(activity: Activity) {
        logDm("onActivityDestroyed ${activity::class.java.simpleName}")

        if (activity.javaClass.name == "leakcanary.internal.activity.LeakActivity") {
            return
        }

        (activity as FragmentActivity)
                .supportFragmentManager
                .unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }
}