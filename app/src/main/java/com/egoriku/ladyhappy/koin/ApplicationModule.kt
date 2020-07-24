package com.egoriku.ladyhappy.koin

import com.egoriku.core.di.utils.*
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.navigation.NavigateMe
import com.egoriku.ladyhappy.navigation.router.Router
import com.egoriku.ladyhappy.tools.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationScopeModule = module {

    factory<IFeatureProvider> { FeatureProvider() }

    single { Authentication() }

    //single<IAppPreferences> { AppPreferences(androidContext()) }
    single<IBlurRendering> { BlurRendering(androidContext()) }
    single<IFirebaseFirestore> { FirebaseFirestore() }
    single<IRemoteConfig> { RemoteConfig() }

    single { NavigateMe.create() }
    single<INavigationHolder> { NavigationHolder(get<NavigateMe<Router>>().navigatorHolder) }
    single<IRouter> { AppRouter(router = get<NavigateMe<Router>>().router) }
}