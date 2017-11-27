package com.egoriku.ladyhappy.di.app

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.firebase.FirebaseAnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideApplication(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
        }
    }

    @Provides
    @Singleton
    fun provideAnalyticsHelper(context: Context): AnalyticsInterface {
        return FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context))
    }

    @Singleton
    @Provides
    fun provideRouter(): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder

/*
     @Provides
     @Singleton
     fun provideSharedPreferences(context: Context): SharedPreferences {
         return PreferenceManager.getDefaultSharedPreferences(context)
     }*/
}