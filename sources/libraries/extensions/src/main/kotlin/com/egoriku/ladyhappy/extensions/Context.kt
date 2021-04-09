@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.OpenableColumns
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import java.io.File
import java.util.*

fun Context.colorFromAttr(@AttrRes attribute: Int) = with(TypedValue()) {
    theme.resolveAttribute(attribute, this, true)
    data
}

fun Context.resIdFromAttr(@AttrRes attribute: Int): Int = with(TypedValue()) {
    theme.resolveAttribute(attribute, this, true)
    resourceId
}

fun Context.colorCompat(@ColorRes colorInt: Int) = ContextCompat.getColor(this, colorInt)

fun Context.colorStateListCompat(@ColorRes resId: Int): ColorStateList? =
        AppCompatResources.getColorStateList(this, resId)

inline fun Context.drawableCompat(@DrawableRes drawableRes: Int) = AppCompatResources.getDrawable(this, drawableRes)

inline fun Context.drawableCompatWithTint(
        @DrawableRes resId: Int,
        @ColorRes tint: Int,
): Drawable? = drawableCompat(resId)?.apply {
    mutate()
    when (tint) {
        0 -> setTintList(null)
        else -> setTint(colorCompat(tint))
    }
}

fun Context.findColorIdByName(name: String): Int = getResourceId(name, type = "color")

fun Context.getResourceId(name: String, type: String): Int =
        resources.getIdentifier(name, type, packageName)

fun Context.getQuantityStringZero(
        @PluralsRes
        pluralResId: Int,
        @StringRes
        zeroResId: Int = -1,
        quantity: Int,
): String = if (zeroResId != -1 && quantity == 0) {
    resources.getString(zeroResId)
} else {
    resources.getQuantityString(pluralResId, quantity, quantity)
}

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

inline fun Context.getFileName(uri: Uri): String {
    var name: String? = when (uri.scheme?.toLowerCase(Locale.US)) {
        "content" -> {
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                } else {
                    null
                }
            }
        }
        else -> null
    }

    if (name.isNullOrEmpty()) {
        name = uri.path

        val cut = name?.lastIndexOf(File.separator) ?: -1

        if (cut != -1) {
            name = name?.substring(cut + 1)
        }
    }

    return requireNotNull(name)
}
