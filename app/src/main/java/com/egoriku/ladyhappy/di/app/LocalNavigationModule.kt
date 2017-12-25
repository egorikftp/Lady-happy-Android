package com.egoriku.ladyhappy.di.app

import com.egoriku.ladyhappy.di.scope.ApplicationScope
import com.egoriku.ladyhappy.navigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides

@Module
class LocalNavigationModule {

    @Provides
    @ApplicationScope
    fun provideLocalNavigationHolder(): LocalCiceroneHolder {
        return LocalCiceroneHolder()
    }
}
