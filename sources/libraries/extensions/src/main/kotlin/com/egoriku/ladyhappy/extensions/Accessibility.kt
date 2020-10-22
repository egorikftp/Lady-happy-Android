@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.view.View

inline fun View.importantForAccessibilityYes() {
    importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
}

inline fun View.importantForAccessibilityNo() {
    importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
}