package com.egoriku.ladyhappy.core.feature

import android.os.Parcelable
import com.egoriku.ladyhappy.core.sharedmodel.params.EditParams
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import kotlinx.parcelize.Parcelize

interface CatalogFeature
interface AboutUsFeature
interface PhotoReportsFeature
interface SettingsFeature
interface DetailPage

sealed class DynamicFeature : Parcelable {

    @Parcelize
    data class PostCreator(
            val postCreatorParams: PostCreatorParams = PostCreatorParams()
    ) : DynamicFeature()

    @Parcelize
    data class Edit(
            val editParams: EditParams
    ) : DynamicFeature()
}