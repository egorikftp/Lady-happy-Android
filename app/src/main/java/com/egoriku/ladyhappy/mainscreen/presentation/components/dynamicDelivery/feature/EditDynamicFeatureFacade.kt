package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.feature

import com.egoriku.ladyhappy.core.sharedmodel.key.EDIT_DYNAMIC_FEATURE
import com.egoriku.ladyhappy.core.splitinstall.ISplitInstallHelper

class EditDynamicFeatureFacade(splitInstallHelper: ISplitInstallHelper) :
    AbstractDynamicFeature(splitInstallHelper) {

    override val moduleName = EDIT_DYNAMIC_FEATURE
}