package com.egoriku.ladyhappy.common

import android.view.View
import android.view.ViewTreeObserver
import kotlin.reflect.KProperty

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}


object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}

class NotNullSingleValueVar<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
            value ?: throw IllegalStateException("${property.name} not initialized")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}