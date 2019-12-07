package com.egoriku.ladyhappy.landing.data.repository.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import javax.inject.Inject

class LandingDataSource
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore)