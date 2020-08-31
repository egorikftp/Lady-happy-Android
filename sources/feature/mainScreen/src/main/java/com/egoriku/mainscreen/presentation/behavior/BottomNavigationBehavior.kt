package com.egoriku.mainscreen.presentation.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class BottomNavigationBehavior(
        context: Context?,
        attrs: AttributeSet?
) : CoordinatorLayout.Behavior<BottomNavigationView>(context, attrs) {

    override fun layoutDependsOn(
            parent: CoordinatorLayout,
            child: BottomNavigationView,
            dependency: View
    ): Boolean = when (dependency) {
        is Snackbar.SnackbarLayout -> true
        else -> super.layoutDependsOn(parent, child, dependency)
    }

    override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: BottomNavigationView,
            directTargetChild: View,
            target: View,
            nestedScrollAxes: Int
    ): Boolean = nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedPreScroll(
            coordinatorLayout: CoordinatorLayout,
            child: BottomNavigationView,
            target: View,
            dx: Int,
            dy: Int,
            consumed: IntArray
    ) {
        if (dy < 0) {
            showBottomNavigationView(child)

            coordinatorLayout.findChildByClass<Snackbar.SnackbarLayout>()?.run {
                animate().translationY(0f)
            }
        } else if (dy > 0) {
            hideBottomNavigationView(child)

            coordinatorLayout.findChildByClass<Snackbar.SnackbarLayout>()?.run {
                animate().translationY(child.height.toFloat())
            }
        }
    }

    private inline fun <reified T : View> ViewGroup.findChildByClass(): T? {
        var result: View? = null

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            if (T::class.isInstance(child)) {
                result = child
            }
        }

        return result as T?
    }

    private fun hideBottomNavigationView(view: BottomNavigationView) {
        view.animate().translationY(view.height.toFloat())
    }

    private fun showBottomNavigationView(view: BottomNavigationView) {
        view.animate().translationY(0f)
    }
}