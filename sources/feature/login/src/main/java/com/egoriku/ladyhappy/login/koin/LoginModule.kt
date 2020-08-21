package com.egoriku.ladyhappy.login.koin

import com.egoriku.ladyhappy.login.presentation.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(
                context = androidContext(),
                authentication = get(),
                router = get()
        )
    }
}