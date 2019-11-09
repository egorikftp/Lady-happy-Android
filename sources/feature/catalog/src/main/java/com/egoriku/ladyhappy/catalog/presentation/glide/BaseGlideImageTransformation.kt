package com.egoriku.ladyhappy.catalog.presentation.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.request.target.Target
import java.security.MessageDigest

abstract class BaseGlideImageTransformation : Transformation<Bitmap> {

    abstract fun getId(): String

    protected abstract fun transform(
            context: Context,
            pool: BitmapPool,
            toTransform: Bitmap,
            outWidth: Int,
            outHeight: Int
    ): Bitmap?

    final override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(getIdBytes())
    }

    override fun transform(
            context: Context,
            resource: Resource<Bitmap>,
            outWidth: Int,
            outHeight: Int
    ): Resource<Bitmap> {
        val bitmapPool = Glide.get(context).bitmapPool
        val toTransform = resource.get()
        val targetWidth = if (outWidth == Target.SIZE_ORIGINAL) toTransform.width else outWidth
        val targetHeight = if (outHeight == Target.SIZE_ORIGINAL) toTransform.height else outHeight
        val transformed = transform(context, bitmapPool, toTransform, targetWidth, targetHeight)

        val result = if (toTransform == transformed) {
            resource
        } else {
            BitmapResource.obtain(transformed, bitmapPool)
        }

        return result ?: throw IllegalStateException()
    }

    override fun hashCode() = getId().hashCode()

    private fun getIdBytes() = getId().byteInputStream().use {
        it.readBytes()
    }
}