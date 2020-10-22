package com.egoriku.ladyhappy.koin

import com.egoriku.ladyhappy.core.*
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.navigation.NavigateMe
import com.egoriku.ladyhappy.navigation.router.Router
import com.egoriku.ladyhappy.tools.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationScopeModule = module {

    factory<IFeatureProvider> { FeatureProvider() }

    single { Authentication() }

    single<IAppPreferences> { AppPreferences(androidContext()) }
    single<IAnalytics> { Analytics(androidContext()) }
    single<IBlurRendering> { BlurRendering(androidContext()) }
    single<IDispatchers> { Dispatchers() }
    single<IFirebase> { Firebase() }
    single<IRemoteConfig> { RemoteConfig() }
    single<IStringResource> { StringResource(androidContext()) }

    single { NavigateMe.create() }
    single<INavigationHolder> { NavigationHolder(get<NavigateMe<Router>>().navigatorHolder) }
    single<IRouter> { AppRouter(router = get<NavigateMe<Router>>().router) }
}