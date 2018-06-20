package com.egoriku.ladyhappy.di.app

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.ladyhappy.App
import dagger.Component

@Component(dependencies = [
    MainToolsProvider::class
])
@ApplicationScope
interface AppComponent : ApplicationProvider {

    fun inject(app: App)

    class Initializer private constructor() {
        companion object {
            fun init(app: App): AppComponent {
                val mainToolsComponent = MainToolsComponent.Initializer.init(app)

                return DaggerAppComponent.builder()
                        .mainToolsProvider(mainToolsComponent)
                        .build()

            }
        }
    }
}