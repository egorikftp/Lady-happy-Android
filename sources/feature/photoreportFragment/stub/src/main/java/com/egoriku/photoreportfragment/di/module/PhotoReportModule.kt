package com.egoriku.photoreportfragment.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.repository.IPhotoReportRepository
import com.egoriku.photoreportfragment.domain.interactor.PhotoReportUseCase
import dagger.Module
import dagger.Provides


@Module
class PhotoReportModule {

    @Provides
    @FragmentScope
    fun provideNewsUseCase(newsRepository: IPhotoReportRepository) = PhotoReportUseCase(newsRepository)
}
