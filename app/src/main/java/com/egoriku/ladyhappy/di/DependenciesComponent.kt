package com.egoriku.ladyhappy.di

import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.DependenciesProvider
import com.egoriku.ladyhappy.di.module.AppModule
import com.egoriku.ladyhappy.di.module.NavigationModule
import com.egoriku.ladyhappy.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    AppModule::class,
    NavigationModule::class,
    ViewModelModule::class
])
@ApplicationScope
interface DependenciesComponent : DependenciesProvider {

    @Component.Builder
    interface Builder {
        fun build(): DependenciesComponent

        @BindsInstance
        fun app(app: IApplication): Builder
    }

    companion object {
        fun init(app: IApplication): DependenciesProvider = DaggerDependenciesComponent.builder().app(app).build()
    }
}