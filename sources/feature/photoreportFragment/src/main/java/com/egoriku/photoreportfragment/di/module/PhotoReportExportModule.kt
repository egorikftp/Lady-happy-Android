package com.egoriku.photoreportfragment.di.module

import com.egoriku.core.actions.IPhotoReportAction
import com.egoriku.photoreportfragment.action.PhotoReportAction
import dagger.Module
import dagger.Provides


@Module
class PhotoReportExportModule {

    @Provides
    fun providePhotoReportFragmentAction(): IPhotoReportAction = PhotoReportAction()
}
