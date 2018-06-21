package com.egoriku.ladyhappy.di.application

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.MainScreenActionsProvider
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.featureactivitymain.di.MainActivityExportComponent
import com.egoriku.ladyhappy.Application
import dagger.Component

@Component(
        dependencies = [
            MainToolsProvider::class,
            MainScreenActionsProvider::class
        ])
@ApplicationScope
interface AppComponent : ApplicationProvider {

    fun inject(app: Application)

    class Initializer private constructor() {
        companion object {
            fun init(app: Application): AppComponent {
                val mainToolsProvider = MainToolsComponent.Initializer
                        .init(app)

                val mainScreenActionsProvider = MainActivityExportComponent.Initializer
                        .init(mainToolsProvider)

                return DaggerAppComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .mainScreenActionsProvider(mainScreenActionsProvider)
                        .build()
            }
        }
    }
}