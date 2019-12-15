package com.egoriku.ladyhappy.koin

import com.egoriku.core.di.utils.IBlurRendering
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.tools.BlurRendering
import com.egoriku.ladyhappy.tools.FeatureProvider
import com.egoriku.ladyhappy.tools.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object ApplicationModule {

    val module = module {

        factory<IFeatureProvider> { FeatureProvider() }

        single<IBlurRendering> { BlurRendering(androidContext()) }
        single<IFirebaseFirestore> { FirebaseFirestore() }
    }
}