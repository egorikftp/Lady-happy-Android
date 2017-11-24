package com.egoriku.ladyhappy.di

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.di.module.AppModule
import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.google.firebase.database.FirebaseDatabase
import dagger.Component


/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */

@ApplicationScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    // Allows to inject into the App
    fun inject(app: App)

    fun context(): Context
    fun app(): App
    fun firebaseDatabase(): FirebaseDatabase
    fun analyticsHelper(): AnalyticsInterface

    /*fun sharedPreferences(): SharedPreferences*/
}