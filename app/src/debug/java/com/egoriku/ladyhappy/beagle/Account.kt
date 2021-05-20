package com.egoriku.ladyhappy.beagle

import com.pandulapeter.beagle.common.configuration.Text
import com.pandulapeter.beagle.common.contracts.BeagleListItemContract

data class Account(
    val email: String,
    val password: String
) : BeagleListItemContract {

    override val id: String = email

    override val title: Text = Text.CharSequence(email)
}
