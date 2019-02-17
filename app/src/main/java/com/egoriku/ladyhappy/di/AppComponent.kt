package com.egoriku.ladyhappy.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.DependenciesProvider
import com.egoriku.ladyhappy.Application
import dagger.Component

@ApplicationScope
@Component(dependencies = [DependenciesProvider::class])
interface AppComponent : ApplicationProvider {

    fun inject(app: Application)

    companion object {
        fun init(app: Application): AppComponent {
            val dependenciesProvider = DependenciesComponent.init(app)

            return DaggerAppComponent.builder()
                    .dependenciesProvider(dependenciesProvider)
                    .build()
        }
    }
}