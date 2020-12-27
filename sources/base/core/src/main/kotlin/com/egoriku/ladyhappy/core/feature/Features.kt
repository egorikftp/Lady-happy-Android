package com.egoriku.ladyhappy.core.feature

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface CatalogFeature
interface AboutUsFeature
interface PhotoReportsFeature
interface SettingsFeature

sealed class DynamicFeature : Parcelable {

    @Parcelize
    object PostCreator : DynamicFeature()
}