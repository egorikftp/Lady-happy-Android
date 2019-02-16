@file:Suppress("UNCHECKED_CAST")

package com.egoriku.featureactivitymain.common

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    if (this !is CoordinatorLayout.LayoutParams) throw IllegalArgumentException("View's layout params should be CoordinatorLayout.LayoutParams")

    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T
            ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}