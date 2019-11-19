package com.egoriku.ladyhappy.koin

import android.app.Application
import com.egoriku.ladyhappy.BuildConfig
import com.egoriku.ladyhappy.catalog.category.koin.CatalogModule
import com.egoriku.ladyhappy.catalog.root.koin.RootCatalogModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.initKoin() {

    startKoin {
        androidContext(this@initKoin)

        if (BuildConfig.DEBUG) {
            androidLogger()
        }


        modules(koinModules)
    }
}

val koinModules = listOf(
        ApplicationModule.module,
        CatalogModule.module,
        RootCatalogModule.module
)