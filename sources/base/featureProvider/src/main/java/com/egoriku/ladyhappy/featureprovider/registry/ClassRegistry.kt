package com.egoriku.ladyhappy.featureprovider.registry

import android.app.Activity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.extensions.castOrNull
import java.util.concurrent.ConcurrentHashMap

internal abstract class ClassRegistry {
    companion object {
        private val CLASS_MAP = ConcurrentHashMap<String, Class<*>>()

        @JvmStatic
        fun <T : Activity> findActivityClass(className: String): Class<T> {
            val clazz = getClass<T>(className)

            if (clazz != null) {
                return clazz
            }

            throw IllegalStateException("Class not found")
        }

        @JvmStatic
        fun <T : Fragment> findFragment(className: String): Fragment {
            val fragment = getClass<T>(className)

            if (fragment != null) {
                return fragment.newInstance()
            }

            throw IllegalArgumentException("Fragment Class is not public or is missing public no-args constructor")
        }

        @JvmStatic
        fun <T : DialogFragment> findDialogFragment(className: String): DialogFragment {
            val fragment = getClass<T>(className)

            if (fragment != null) {
                return fragment.newInstance()
            }

            throw IllegalArgumentException("Fragment Class is not public or is missing public no-args constructor")
        }


        private fun <T> getClass(className: String): Class<T>? {
            return CLASS_MAP.getOrPut(className) {
                try {
                    Class.forName(className)
                } catch (e: ClassNotFoundException) {
                    return null
                }
            }.castOrNull()
        }
    }
}
