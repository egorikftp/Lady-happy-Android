package com.egoriku.ladyhappy.koin

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val debugModule = module {
    single {
        AccountProvider(androidContext())
    }
}