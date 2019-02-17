package com.egoriku.photoreport.domain.interactor

import com.egoriku.photoreport.data.repository.IPhotoReportRepository
import javax.inject.Inject

internal class PhotoReportUseCase
@Inject constructor(private val photoReportRepository: IPhotoReportRepository) {

    suspend fun getPhotoReportInfo() = photoReportRepository.getPhotoReport()
}