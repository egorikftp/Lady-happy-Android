package com.egoriku.ladyhappy.domain.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.egoriku.featureactivitymain.common.Constants
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class SingleCategoryModel(
        val id: Int = 0,
        val key: String = Constants.EMPTY,
        val title: String = Constants.EMPTY,
        val imageUrl: String = Constants.EMPTY
) : Parcelable

data class CategoriesModel(val categories: List<SingleCategoryModel> = listOf())