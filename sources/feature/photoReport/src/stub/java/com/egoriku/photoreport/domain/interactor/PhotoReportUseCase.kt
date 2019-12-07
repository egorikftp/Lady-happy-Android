package com.egoriku.photoreport.domain.interactor

import com.egoriku.photoreport.data.repository.IPhotoReportRepository
import javax.inject.Inject

class PhotoReportUseCase
@Inject constructor(private val photoReportRepository: IPhotoReportRepository)