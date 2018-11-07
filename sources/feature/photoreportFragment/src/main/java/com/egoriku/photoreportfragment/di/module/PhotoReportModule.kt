package com.egoriku.photoreportfragment.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.repository.IPhotoReportRepository
import com.egoriku.photoreportfragment.domain.interactor.PhotoReportUseCase
import com.egoriku.photoreportfragment.presentation.PhotoReportContract
import com.egoriku.photoreportfragment.presentation.PhotoReportPresenter
import dagger.Module
import dagger.Provides


@Module
class PhotoReportModule {

    @Provides
    @FragmentScope
    fun providesAllGoodsPresenter(
            newsUseCase: PhotoReportUseCase,
            analyticsInterface: IAnalytics
    ): PhotoReportContract.Presenter = PhotoReportPresenter(newsUseCase, analyticsInterface)

    @Provides
    @FragmentScope
    fun provideNewsUseCase(newsRepository: IPhotoReportRepository) = PhotoReportUseCase(newsRepository)
}
