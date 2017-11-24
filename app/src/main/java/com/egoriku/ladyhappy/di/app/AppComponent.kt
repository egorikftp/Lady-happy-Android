package com.egoriku.ladyhappy.di.app

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.google.firebase.firestore.FirebaseFirestore
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
    fun firebaseFirestore(): FirebaseFirestore
    fun analyticsHelper(): AnalyticsInterface
}