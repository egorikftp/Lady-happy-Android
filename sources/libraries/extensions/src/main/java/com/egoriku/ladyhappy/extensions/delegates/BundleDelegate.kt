package com.egoriku.ladyhappy.extensions.delegates

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class BundleDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<Bundle, T> {
    class Int(key: kotlin.String) : BundleDelegate<kotlin.Int>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>) = thisRef.getInt(key)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.Int) = thisRef.putInt(key, value)
    }

    class String(key: kotlin.String) : BundleDelegate<kotlin.String>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>) = thisRef.getString(key)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.String) = thisRef.putString(key, value)
    }
}