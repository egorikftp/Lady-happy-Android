package com.egoriku.core.repository

import com.egoriku.core.model.IComplexPhotoReportModel
import io.reactivex.Observable

interface IPhotoReportRepository {

    fun getPhotoReportInfo(): Observable<IComplexPhotoReportModel>
}