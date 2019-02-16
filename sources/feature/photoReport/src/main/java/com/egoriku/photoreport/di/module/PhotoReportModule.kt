package com.egoriku.photoreport.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.photoreport.data.repository.IPhotoReportRepository
import com.egoriku.photoreport.data.repository.datasource.PhotoReportDataSource
import com.egoriku.photoreport.domain.interactor.PhotoReportUseCase
import com.egoriku.photoreport.presentation.PhotoReportContract
import com.egoriku.photoreport.presentation.PhotoReportPresenter
import com.egoriku.photoreport.data.repository.PhotoReportRepository
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
