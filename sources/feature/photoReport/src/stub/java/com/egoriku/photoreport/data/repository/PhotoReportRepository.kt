package com.egoriku.photoreport.data.repository

import com.egoriku.photoreport.data.repository.datasource.PhotoReportDataSource

class PhotoReportRepository(
        private val photoReportDataSource: PhotoReportDataSource
) : IPhotoReportRepository

interface IPhotoReportRepository