package com.egoriku.ladyhappy.glide.transformations

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader.TileMode
import androidx.annotation.ColorRes
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import java.nio.ByteBuffer
import java.security.MessageDigest

class GradientOverlayTransformation(
        @ColorRes
        private val startColor: Int,

        @ColorRes
        private val centerColor: Int,

        @ColorRes
        private val endColor: Int
) : BitmapTransformation() {

    override fun equals(other: Any?) = other is GradientOverlayTransformation &&
            other.startColor == startColor &&
            other.centerColor == centerColor &&
            other.endColor == endColor

    override fun hashCode(): Int {
        var result: Int = 31 * startColor
        result = 31 * result + centerColor
        result = 31 * result + endColor

        return Util.hashCode(ID.hashCode(), result)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)

        val radiusData = ByteBuffer
                .allocate(12)
                .putInt(startColor)
                .putInt(centerColor)
                .putInt(endColor)
                .array()
        messageDigest.update(radiusData)
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val bitmap = pool[toTransform.width, toTransform.height, Bitmap.Config.ARGB_8888]

        val canvas = Canvas(bitmap).apply {
            drawBitmap(toTransform, 0f, 0f, null)
        }

        val shader = LinearGradient(
                toTransform.width / 2f,
                0f,
                bitmap.width / 2f,
                bitmap.height.toFloat(),
                intArrayOf(startColor, centerColor, endColor),
                floatArrayOf(0f, 0.5f, 1f),
                TileMode.CLAMP
        )

        val paint = Paint().apply {
            setShader(shader)
        }

        canvas.drawRect(
                0f,
                0f,
                bitmap.width.toFloat(),
                bitmap.height.toFloat(),
                paint
        )

        return bitmap
    }

    companion object {
        private const val ID = "com.egoriku.ladyhappy.glide.transformations.GradientTransformation"

        private val ID_BYTES = ID.toByteArray(Key.CHARSET)
    }
}