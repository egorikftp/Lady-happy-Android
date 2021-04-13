package com.egoriku.ladyhappy.core.splitinstall

interface ISplitInstallHelper {

    fun installModule(moduleName: String, moduleInstallListener: ModuleInstallListener)
}