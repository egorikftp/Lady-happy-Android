package com.egoriku.ladyhappy.di.app

import android.app.Application
import android.content.Context
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.google.firebase.firestore.FirebaseFirestore
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@ApplicationScope
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    NavigationModule::class,
    ActivityBuilder::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
    fun context(): Context

    fun firebaseFirestore(): FirebaseFirestore
    fun analyticsHelper(): AnalyticsInterface
    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
}