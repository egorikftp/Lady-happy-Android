package com.egoriku.ladyhappy.di.application

import com.egoriku.core.di.*
import com.egoriku.featureactivitymain.di.MainActivityExportComponent
import com.egoriku.ladyhappy.Application
import com.egoriku.mainfragment.di.MainFragmentExportComponent
import dagger.Component

@Component(
        dependencies = [
            MainToolsProvider::class,
            MainActivityProvider::class,
            MainFragmentProvider::class
        ])
@ApplicationScope
interface AppComponent : ApplicationProvider {

    fun inject(app: Application)

    class Initializer private constructor() {
        companion object {
            fun init(app: Application): AppComponent {
                val mainToolsProvider = MainToolsComponent.Initializer.init(app)
                val mainActivityProvider = MainActivityExportComponent.Initializer.init()
                val mainFragmentProvider = MainFragmentExportComponent.Initializer.init()

                return DaggerAppComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .mainActivityProvider(mainActivityProvider)
                        .mainFragmentProvider(mainFragmentProvider)
                        .build()
            }
        }
    }
}