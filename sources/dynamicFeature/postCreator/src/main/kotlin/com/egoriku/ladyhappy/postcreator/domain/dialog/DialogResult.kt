package com.egoriku.ladyhappy.postcreator.domain.dialog

import android.os.Parcelable
import com.egoriku.ladyhappy.postcreator.domain.predefined.ColorModel
import kotlinx.parcelize.Parcelize

sealed class DialogResult : Parcelable {

    @Parcelize
    data class Category(val category: String) : DialogResult()

    @Parcelize
    data class SubCategory(val subCategory: String) : DialogResult()

    @Parcelize
    data class Color(val colorIds: List<ColorModel>) : DialogResult()

    @Parcelize
    data class CreationDate(val dateInMilliseconds: Long) : DialogResult()
}