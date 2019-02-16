package com.egoriku.photoreportfragment.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.photoreportfragment.data.repository.IPhotoReportRepository
import com.egoriku.photoreportfragment.data.repository.datasource.PhotoReportDataSource
import com.egoriku.photoreportfragment.domain.interactor.PhotoReportUseCase
import com.egoriku.photoreportfragment.presentation.PhotoReportContract
import com.egoriku.photoreportfragment.presentation.PhotoReportPresenter
import com.egoriku.photoreportfragment.data.repository.PhotoReportRepository
import dagger.Module
import dagger.Provides

@Module
internal class PhotoReportModule {

    @Provides
    @FragmentScope
    fun providesPhotoReportPresenter(
            newsUseCase: PhotoReportUseCase,
            analyticsInterface: IAnalytics
    ): PhotoReportContract.Presenter = PhotoReportPresenter(newsUseCase, analyticsInterface)

    @Provides
    @FragmentScope
    fun provideNewsUseCase(newsRepository: IPhotoReportRepository) = PhotoReportUseCase(newsRepository)

    @Provides
    fun providePhotoReportRepo(photoReportDataSource: PhotoReportDataSource): IPhotoReportRepository = PhotoReportRepository(photoReportDataSource)

    @Provides
    fun providePhotoReportDataSource(firebaseFirestore: IFirebaseFirestore) = PhotoReportDataSource(firebaseFirestore)
}
