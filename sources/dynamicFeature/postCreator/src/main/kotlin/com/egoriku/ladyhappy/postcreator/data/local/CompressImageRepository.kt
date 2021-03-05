package com.egoriku.ladyhappy.postcreator.data.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File

class CompressImageRepository(private val context: Context) {

    suspend fun resizeImage(file: File): File = withContext(Dispatchers.IO) {
        Compressor.compress(context, file) {
            default(width = 600, height = 1000, quality = 100)
        }
    }

    suspend fun convertFileToByteArray(file: File): ByteArray = withContext(Dispatchers.IO) {
        BitmapFactory.decodeFile(file.absolutePath).run {
            val stream = ByteArrayOutputStream().apply {
                compress(Bitmap.CompressFormat.JPEG, 100, this)
            }

            stream.toByteArray()
        }
    }

    enum class SIZE(val width: Int) {
        PREVIEW(600),
        LARGE(2200)
    }
}