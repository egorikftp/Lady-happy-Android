package com.egoriku.ladyhappy.di.app

import android.app.Application
import android.content.Context
import com.egoriku.core.di.ApplicationScope
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.firebase.FirebaseAnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: Application): Context = application

    @Provides
    @ApplicationScope
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setTimestampsInSnapshotsEnabled(true)
                .build()
    }

    @Provides
    @ApplicationScope
    fun provideAnalyticsHelper(context: Context): AnalyticsInterface = FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context))
}