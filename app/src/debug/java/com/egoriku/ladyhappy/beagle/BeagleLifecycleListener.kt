package com.egoriku.ladyhappy.beagle

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.extensions.listeners.SimpleActivityLifecycleCallbacks
import com.egoriku.ladyhappy.koin.AccountProvider
import com.egoriku.ladyhappy.login.presentation.LoginFragment
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.modules.ItemListModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val ID_LOGIN_SECTION = "id_login_section"

internal class BeagleLifecycleListener : SimpleActivityLifecycleCallbacks(), KoinComponent {

    private val accountProvider: AccountProvider by inject()

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentViewCreated(
                fm: FragmentManager,
                fragment: Fragment,
                view: View,
                savedInstanceState: Bundle?
        ) {
            if (fragment is LoginFragment && !Beagle.contains(ID_LOGIN_SECTION)) {
                Beagle.add(
                        ItemListModule(
                                id = ID_LOGIN_SECTION,
                                title = "Test accounts",
                                items = accountProvider.accounts,
                                isExpandedInitially = true,
                                onItemSelected = { account ->
                                    view.findViewById<EditText>(R.id.loginEmail)?.setText(account.email)
                                    view.findViewById<EditText>(R.id.loginPassword)?.setText(account.password)

                                    Beagle.hide()
                                }
                        ),
                        lifecycleOwner = fragment
                )
            }
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
        }
    }
}