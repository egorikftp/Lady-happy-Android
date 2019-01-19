package com.egoriku.ladyhappy.di.module

import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.tools.Analytics
import com.egoriku.ladyhappy.tools.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideFirebaseFirestore(): IFirebaseFirestore = FirebaseFirestore()

    @Provides
    @ApplicationScope
    fun provideAnalyticsHelper(app: IApplication): IAnalytics = Analytics(app.getApplicationContext())
}