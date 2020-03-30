package com.egoriku.ladyhappy.beagle

import com.pandulapeter.beagleCore.contracts.BeagleListItemContract

data class Account(
        override val name: String,
        val password: String
) : BeagleListItemContract
