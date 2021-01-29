package com.egoriku.ladyhappy.catalog.subcategory.presentation.screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_DETAIL_PAGE_EXTRA
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough

class DetailPageScreen(
        private val featureProvider: IFeatureProvider,
        private val subCategoryItem: SubCategoryItem,
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.detailPage.apply {
            enterTransition = MaterialFadeThrough()
            returnTransition = MaterialFade()

            arguments = bundleOf(
                    KEY_DETAIL_PAGE_EXTRA to DetailPageParams(
                            subCategoryId = subCategoryItem.id,
                            productName = subCategoryItem.name,
                            productLogoUrl = subCategoryItem.images.first().url,
                            productDescription = EMPTY
                    )
            )
        }
}