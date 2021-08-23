package com.egoriku.plugin

open class HappyXPluginExtension {

    /**
     * configure BuildConfig generation
     */
    open var buildConfigGeneration = false

    /**
     * apply "kotlin-parcelize" plugin
     */
    open var kotlinParcelize = false

    /**
     * configure Compose
     */
    open var compose = false

    /**
     * configure ViewBinding
     */
    open var viewBinding = false
}