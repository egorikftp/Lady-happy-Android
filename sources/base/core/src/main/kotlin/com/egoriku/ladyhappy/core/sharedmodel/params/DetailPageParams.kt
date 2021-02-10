package com.egoriku.ladyhappy.core.sharedmodel.params

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailPageParams(
        val categoryId: Int,
        val subCategoryId: Int,
        val productName: String,
        val productLogoUrl: String,
        val productDescription: String,
) : Parcelable
