package com.egoriku.ladyhappy.di.app

import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */

@ApplicationScope
@Component(modules = [AppModule::class, NavigationModule::class])
interface AppComponent {

    fun inject(app: App)

    fun context(): Context
    fun app(): App
    fun firebaseFirestore(): FirebaseFirestore
    fun analyticsHelper(): AnalyticsInterface
    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
}