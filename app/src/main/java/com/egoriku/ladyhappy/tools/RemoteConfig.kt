package com.egoriku.ladyhappy.tools

import com.egoriku.ladyhappy.core.IRemoteConfig
import com.egoriku.ladyhappy.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val FETCH_TIME = 3600L

internal class RemoteConfig : IRemoteConfig {

    private val firebaseRemoteConfig = Firebase.remoteConfig

    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = FETCH_TIME
    }

    init {
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_defaults)
    }

    override fun fetch() {
        firebaseRemoteConfig.fetchAndActivate()
    }
}