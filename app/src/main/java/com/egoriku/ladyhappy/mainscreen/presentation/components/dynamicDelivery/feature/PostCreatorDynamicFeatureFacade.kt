package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.feature

import com.egoriku.ladyhappy.core.sharedmodel.key.POST_CREATOR_DYNAMIC_FEATURE
import com.egoriku.ladyhappy.core.splitinstall.ISplitInstallHelper

class PostCreatorDynamicFeatureFacade(
    splitInstallHelper: ISplitInstallHelper
) : AbstractDynamicFeature(splitInstallHelper) {

    override val moduleName = POST_CREATOR_DYNAMIC_FEATURE
}