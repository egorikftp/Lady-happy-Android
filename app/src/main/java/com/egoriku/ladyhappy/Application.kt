package com.egoriku.ladyhappy

import androidx.appcompat.app.AppCompatDelegate
import com.egoriku.core.IRemoteConfig
import com.egoriku.core.sharedmodel.Theme
import com.egoriku.core.sharedmodel.toNightMode
import com.egoriku.ladyhappy.koin.initKoin
import com.egoriku.ladyhappy.settings.domain.usecase.theme.GetThemeUseCase
import com.egoriku.network.ResultOf
import com.google.android.play.core.splitcompat.SplitCompatApplication
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

open class Application : SplitCompatApplication() {

    private val currentGetThemeUseCase: GetThemeUseCase by inject()
    private val remoteConfig: IRemoteConfig by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()

        remoteConfig.fetch()

        runBlocking {
            currentGetThemeUseCase(Unit).let {
                if (it is ResultOf.Success) it.value else Theme.SYSTEM
            }
        }.also {
            AppCompatDelegate.setDefaultNightMode(it.toNightMode())
        }
    }
}

