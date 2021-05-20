package com.egoriku.ladyhappy.core.sharedmodel.mapper

import com.egoriku.ladyhappy.core.sharedmodel.entity.ImageEntity
import com.egoriku.ladyhappy.mozaik.model.MozaikItem

class ImageEntityMapper : (ImageEntity) -> MozaikItem {

    override fun invoke(entity: ImageEntity) = MozaikItem(
        width = entity.width,
        height = entity.height,
        url = entity.url
    )
}