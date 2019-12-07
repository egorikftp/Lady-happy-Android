package com.egoriku.ladyhappy.catalog.category.presentation.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.egoriku.core.di.utils.IBlurRendering
import org.koin.core.KoinComponent
import org.koin.core.inject

class BlurTransformation(
        private val radius: Float
) : BaseGlideImageTransformation(), KoinComponent {

    private val rendering: IBlurRendering by inject()

    override fun getId() = "com.egoriku.ladyhappy.catalog.category.presentation.glide.BlurTransformation"

    override fun transform(
            context: Context,
            pool: BitmapPool,
            toTransform: Bitmap,
            outWidth: Int,
            outHeight: Int
    ): Bitmap? {
        val width = toTransform.width
        val height = toTransform.height

        val bitmap: Bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val paint = Paint().apply {
            flags = Paint.FILTER_BITMAP_FLAG
        }

        canvas.drawBitmap(toTransform, 0f, 0f, paint)

        return rendering.applyBlur(radius = radius, bitmap = bitmap)
    }

    override fun hashCode() = getId().hashCode()

    override fun equals(other: Any?) = other is BlurTransformation
}