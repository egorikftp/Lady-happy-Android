package com.egoriku.photoreportfragment.domain.interactor

import com.egoriku.core.repository.IPhotoReportRepository
import javax.inject.Inject

internal class PhotoReportUseCase
@Inject constructor(private val photoReportRepository: IPhotoReportRepository) {

    suspend fun getPhotoReportInfo() = photoReportRepository.getPhotoReport()
}