package com.egoriku.ladyhappy.di.application

import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.ladyhappy.di.application.module.AppModule
import com.egoriku.ladyhappy.di.application.module.NavigationModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    AppModule::class,
    NavigationModule::class
])
@ApplicationScope
interface MainToolsComponent : MainToolsProvider {

    @Component.Builder
    interface Builder {
        fun build(): MainToolsComponent

        @BindsInstance
        fun app(app: IApplication): Builder
    }

    class Initializer private constructor() {
        companion object {

            fun init(app: IApplication): MainToolsProvider = DaggerMainToolsComponent.builder()
                            .app(app)
                            .build()
        }
    }
}