package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

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

fun Context.drawableCompat(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

fun Context.findColorIdByName(name: String): Int = getResourceId(name, type = "color")

fun Context.getResourceId(name: String, type: String): Int =
        resources.getIdentifier(name, type, packageName)

val Context.inputWindowManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.bitmapFromUri(uri: Uri): Bitmap {
    val contentResolver = contentResolver

    return if (hasP()) {
        ImageDecoder.createSource(contentResolver, uri).run {
            ImageDecoder.decodeBitmap(this)
        }
    } else {
        MediaStore.Images.Media.getBitmap(contentResolver, uri)
    }
}

fun Context.getQuantityStringZero(
        @PluralsRes
        pluralResId: Int,
        @StringRes
        zeroResId: Int = -1,
        quantity: Int
): String = if (zeroResId != -1 && quantity == 0) {
    resources.getString(zeroResId)
} else {
    resources.getQuantityString(pluralResId, quantity, quantity)
}