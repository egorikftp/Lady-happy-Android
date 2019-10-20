package com.egoriku.ladyhappy.di

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.photoreport.data.repository.IPhotoReportRepository
import com.egoriku.photoreport.data.repository.PhotoReportRepository
import com.egoriku.photoreport.data.repository.datasource.PhotoReportDataSource
import com.egoriku.photoreport.domain.interactor.PhotoReportUseCase
import dagger.Module
import dagger.Provides

@Module
@Deprecated("Should be inside feature after migration to Koin")
class PhotoReportModule {

    @Provides
    fun provideNewsUseCase(newsRepository: IPhotoReportRepository) = PhotoReportUseCase(newsRepository)

    @Provides
    fun providePhotoReportRepo(photoReportDataSource: PhotoReportDataSource): IPhotoReportRepository = PhotoReportRepository(photoReportDataSource)

    @Provides
    fun providePhotoReportDataSource(firebaseFirestore: IFirebaseFirestore) = PhotoReportDataSource(firebaseFirestore)
}
