package com.egoriku.ladyhappy.koin

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.tools.FeatureProvider
import com.egoriku.ladyhappy.tools.FirebaseFirestore
import org.koin.dsl.module

object ApplicationModule {

    val module = module {

        factory<IFeatureProvider> { FeatureProvider() }

        single<IFirebaseFirestore> { FirebaseFirestore() }
    }
}