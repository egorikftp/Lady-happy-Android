package com.egoriku.photoreportfragment.domain.interactor

import com.egoriku.photoreportfragment.data.repository.IPhotoReportRepository
import javax.inject.Inject

internal class PhotoReportUseCase
@Inject constructor(private val photoReportRepository: IPhotoReportRepository) {

    suspend fun getPhotoReportInfo() = photoReportRepository.getPhotoReport()
}