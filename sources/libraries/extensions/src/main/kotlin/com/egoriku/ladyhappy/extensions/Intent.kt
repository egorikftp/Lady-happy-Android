package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

fun Fragment.browseUrl(url: String, newTask: Boolean = false) =
    requireContext().browseUrl(url, newTask)

fun Context.browseUrl(url: String, newTask: Boolean = false) {
    runCatching {
        startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)

                if (newTask) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
        )
    }.getOrElse {
        logD("${it.message}")
    }
}

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any>): Intent =
    createIntent(ctx = requireActivity(), clazz = T::class.java, params = params)

fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any>>) =
    Intent(ctx, clazz).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    }

private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any>>) {
    params.forEach {
        when (val value = it.second) {
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw IllegalArgumentException(
                    "Intent extra ${it.first} has wrong type ${value.javaClass.name}"
                )
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw IllegalArgumentException(
                "Intent extra ${it.first} has wrong type ${value.javaClass.name}"
            )
        }
        return@forEach
    }
}
