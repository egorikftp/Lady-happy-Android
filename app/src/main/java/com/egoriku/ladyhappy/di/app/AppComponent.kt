package com.egoriku.ladyhappy.di.app

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Component
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    // Allows to inject into the App
    fun inject(app: App)

    fun context(): Context
    fun app(): App
    fun firebaseFirestore(): FirebaseFirestore
    fun analyticsHelper(): AnalyticsInterface
    fun getRouter(): Router
}