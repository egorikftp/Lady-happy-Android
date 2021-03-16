package com.egoriku.ladyhappy.core.sharedmodel.entity

import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.CATEGORY_ID
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.DESCRIPTION
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.DOCUMENT_REFERENCE
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.IMAGES
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.IS_POPULAR
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.LAST_EDIT_TIME
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.NAME
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.PUBLISHED_COUNT
import com.egoriku.ladyhappy.core.sharedmodel.key.DocumentField.SUB_CATEGORY_ID
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName
import java.util.*

class SubCategoryEntity(
        @PropertyName(CATEGORY_ID)
        @JvmField
        val categoryId: Int = -1,

        @PropertyName(SUB_CATEGORY_ID)
        @JvmField
        val subCategoryId: Int = -1,

        @PropertyName(NAME)
        @JvmField
        val subCategoryName: String = EMPTY,

        @PropertyName(IS_POPULAR)
        @JvmField
        val isPopular: Boolean = false,

        @PropertyName(IMAGES)
        @JvmField
        val images: List<ImageEntity> = emptyList(),

        @PropertyName(PUBLISHED_COUNT)
        @JvmField
        val publishedCount: Int = 0,

        @PropertyName(DESCRIPTION)
        @JvmField
        val description: String = EMPTY,

        @PropertyName(DOCUMENT_REFERENCE)
        @JvmField
        val documentReference: String = EMPTY,

        @PropertyName(LAST_EDIT_TIME)
        @JvmField
        val lastEditTime: Date? = null
)