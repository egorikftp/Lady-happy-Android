package com.egoriku.photoreportfragment.domain.interactor

import com.egoriku.core.repository.IPhotoReportRepository
import com.egoriku.core.usecase.BaseUseCase
import com.egoriku.core.usecase.Params
import javax.inject.Inject

class PhotoReportUseCase
@Inject constructor(private val photoReportRepository: IPhotoReportRepository) : BaseUseCase() {

    override fun getObservable(params: Params?) = photoReportRepository.getPhotoReportInfo()
}