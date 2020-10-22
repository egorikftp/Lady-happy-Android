package com.egoriku.ladyhappy.postcreator.data.local.util

import androidx.core.graphics.scale
import id.zelory.compressor.constraint.Compression
import id.zelory.compressor.constraint.Constraint
import id.zelory.compressor.loadBitmap
import id.zelory.compressor.overWrite
import java.io.File

class ResizeScaledConstraint(private val width: Int) : Constraint {

    private var isResolved = false

    override fun isSatisfied(imageFile: File): Boolean = isResolved

    override fun satisfy(imageFile: File): File {
        val loadBitmap = loadBitmap(imageFile)

        val proportion = loadBitmap.width.toFloat() / loadBitmap.height.toFloat()
        val newHeight = (width / proportion).toInt()

        isResolved = true

        return overWrite(
                imageFile = imageFile,
                bitmap = loadBitmap.scale(width = width, height = newHeight),
                quality = 80
        )
    }
}

fun Compression.resizeScaled(width: Int) {
    constraint(ResizeScaledConstraint(width))
}