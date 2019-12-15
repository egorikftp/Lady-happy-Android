package com.egoriku.photoreport.data.repository.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import javax.inject.Inject

class PhotoReportDataSource
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore)