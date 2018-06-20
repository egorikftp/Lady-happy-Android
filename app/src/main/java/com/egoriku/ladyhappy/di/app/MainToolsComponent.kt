package com.egoriku.ladyhappy.di.app

import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.MainToolsProvider
import com.egoriku.ladyhappy.App
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@ApplicationScope
interface MainToolsComponent : MainToolsProvider {

    @Component.Builder
    interface Builder {
        fun build(): MainToolsComponent
        @BindsInstance
        fun app(app: App): Builder
    }

    class Initializer private constructor() {
        companion object {

            fun init(app: App): MainToolsProvider =
                    DaggerMainToolsComponent.builder()
                            .app(app)
                            .build()
        }
    }
}