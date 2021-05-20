package com.egoriku.ladyhappy.catalog.subcategory.presentation.screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_DETAIL_PAGE_EXTRA
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class DetailPageScreen(
    private val featureProvider: IFeatureProvider,
    private val subCategoryModel: SubCategoryModel
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.detailPage.apply {
            arguments = bundleOf(
                KEY_DETAIL_PAGE_EXTRA to DetailPageParams(
                    categoryId = subCategoryModel.categoryId,
                    subCategoryId = subCategoryModel.subCategoryId,
                    productName = subCategoryModel.subCategoryName,
                    productLogoUrl = subCategoryModel.images.first().url,
                    productDescription = subCategoryModel.description
                )
            )
        }
}