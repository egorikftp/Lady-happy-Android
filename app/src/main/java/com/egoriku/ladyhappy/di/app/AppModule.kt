package com.egoriku.ladyhappy.di.app

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.firebase.FirebaseAnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    @ApplicationScope
    fun provideApplication(): App = app

    @Provides
    @ApplicationScope
    fun provideContext(): Context = app

    @Provides
    @ApplicationScope
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
        }
    }

    @Provides
    @ApplicationScope
    fun provideAnalyticsHelper(context: Context): AnalyticsInterface {
        return FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context))
    }

/*
     @Provides
     @ApplicationScope
     fun provideSharedPreferences(context: Context): SharedPreferences {
         return PreferenceManager.getDefaultSharedPreferences(context)
     }*/
}