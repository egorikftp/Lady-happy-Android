package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.feature

import com.egoriku.ladyhappy.core.splitinstall.ISplitInstallHelper
import com.egoriku.ladyhappy.core.splitinstall.ModuleInstallListener

abstract class AbstractDynamicFeature(private val splitInstallHelper: ISplitInstallHelper) {

    abstract val moduleName: String

    fun installModule(moduleInstallListener: ModuleInstallListener) =
        splitInstallHelper.installModule(moduleName, moduleInstallListener)
}