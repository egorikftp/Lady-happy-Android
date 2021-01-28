package com.egoriku.ladyhappy.core.feature

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface CatalogFeature
interface AboutUsFeature
interface PhotoReportsFeature
interface SettingsFeature
interface DetailPage

sealed class DynamicFeature : Parcelable {

    @Parcelize
    object PostCreator : DynamicFeature()
}