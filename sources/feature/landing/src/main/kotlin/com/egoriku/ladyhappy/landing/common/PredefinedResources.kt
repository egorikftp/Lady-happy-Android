package com.egoriku.ladyhappy.landing.common

import com.egoriku.landing.R

object PredefinedResources {

    private val iconDrawables = hashMapOf(
            "VK" to R.drawable.ic_vk,
            "TELEGRAM" to R.drawable.ic_telegram,
            "GITHUB" to R.drawable.ic_github,
            "ODNOKLASSNIKI" to R.drawable.ic_odnoklassniki,
            "INSTAGRAM" to R.drawable.ic_instagram
    )

    fun getDrawableByType(type: String): Int {
        iconDrawables.forEach {
            if (type == it.key) {
                return it.value
            }
        }
        return 0
    }
}