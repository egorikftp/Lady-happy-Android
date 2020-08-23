package com.egoriku.core.feature

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface CatalogFeature
interface AboutUsFeature
interface PhotoReportsFeature
interface SettingsFeature

sealed class DynamicFeature : Parcelable {

    @Parcelize
    object PostCreator : DynamicFeature()
}