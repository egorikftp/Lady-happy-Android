package com.egoriku.ladyhappy.di

import com.egoriku.core.di.*
import com.egoriku.featureactivitymain.di.MainActivityFeatureSharedComponent
import com.egoriku.ladyhappy.Application
import com.egoriku.landingfragment.di.LandingSharedComponent
import com.egoriku.photoreportfragment.di.PhotoReportSharedComponent
import com.egoriku.settings.di.SettingsFeatureSharedComponent
import dagger.Component

@Component(
        dependencies = [
            DependenciesProvider::class,
            MainActivityFeatureProvider::class,
            LandingFeatureProvider::class,
            PhotoReportFeatureProvider::class,
            SettingsFeatureProvider::class
        ])
@ApplicationScope
interface AppComponent : ApplicationProvider {

    fun inject(app: Application)

    companion object {
        fun init(app: Application): AppComponent {
            val dependenciesProvider = DependenciesComponent.init(app)
            val mainActivityFeature = MainActivityFeatureSharedComponent.init()

            val landingFeature = LandingSharedComponent.init()
            val photoReportFeature = PhotoReportSharedComponent.init()
            val settingsFeature = SettingsFeatureSharedComponent.init()

            return DaggerAppComponent.builder()
                    .dependenciesProvider(dependenciesProvider)
                    .mainActivityFeatureProvider(mainActivityFeature)
                    .landingFeatureProvider(landingFeature)
                    .photoReportFeatureProvider(photoReportFeature)
                    .settingsFeatureProvider(settingsFeature)
                    .build()
        }
    }
}