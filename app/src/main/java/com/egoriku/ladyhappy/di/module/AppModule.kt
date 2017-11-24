package com.egoriku.ladyhappy.di.module

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.google.firebase.database.FirebaseDatabase
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
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance().apply {
            setPersistenceEnabled(true)
        }
    }

    /* @Provides
     @ApplicationScope
     fun provideAnalyticsHelper(context: Context): AnalyticsInterface {
         return FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context))
     }

     @Provides
     @ApplicationScope
     fun provideSharedPreferences(context: Context): SharedPreferences {
         return PreferenceManager.getDefaultSharedPreferences(context)
     }*/
}