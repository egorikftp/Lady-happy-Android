package com.egoriku.ladyhappy.koin

import android.app.Application
import com.egoriku.ladyhappy.BuildConfig
import com.egoriku.ladyhappy.catalog.categories.koin.categoriesModule
import com.egoriku.ladyhappy.catalog.subcategory.koin.subcategoryModule
import com.egoriku.ladyhappy.login.koin.loginModule
import com.egoriku.ladyhappy.settings.koin.settingsModule
import com.egoriku.ladyhappy.landing.koin.landingModule
import com.egoriku.ladyhappy.mainscreen.koin.mainActivityModule
import com.egoriku.ladyhappy.photoreport.koin.photoReportModule
import com.egoriku.usedLibraries.koin.usedLibrariesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun Application.initKoin() {

    startKoin {
        androidContext(this@initKoin)

        if (BuildConfig.DEBUG) {
            androidLogger(Level.ERROR)
        }

        androidFileProperties()

        modules(koinModules)
    }
}

val koinModules = listOf(
        applicationScopeModule,
        categoriesModule,
        landingModule,
        loginModule,
        mainActivityModule,
        photoReportModule,
        settingsModule,
        subcategoryModule,
        usedLibrariesModule
)