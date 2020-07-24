package com.egoriku.ladyhappy.tools

import com.egoriku.core.di.utils.IRemoteConfig
import com.egoriku.ladyhappy.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

internal class RemoteConfig : IRemoteConfig {

    private val remoteConfig = Firebase.remoteConfig

    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_defaults)
    }

    override fun fetch() {
        remoteConfig.fetchAndActivate()
    }
}