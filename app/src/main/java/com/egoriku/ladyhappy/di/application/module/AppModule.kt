package com.egoriku.ladyhappy.di.application.module

import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.di.tools.AnalyticsHelperImpl
import com.egoriku.ladyhappy.di.tools.FirebaseFirestoreImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ApplicationScope
        fun provideFirebaseFirestore(): IFirebaseFirestore = FirebaseFirestoreImpl()

        @JvmStatic
        @Provides
        @ApplicationScope
        fun provideAnalyticsHelper(app: IApplication): IAnalyticsHelper = AnalyticsHelperImpl(app.getApplicationContext())
    }
}