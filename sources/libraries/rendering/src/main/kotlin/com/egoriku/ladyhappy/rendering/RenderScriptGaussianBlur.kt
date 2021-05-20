package com.egoriku.ladyhappy.rendering

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.annotation.FloatRange

class RenderScriptGaussianBlur(context: Context) {

    private val renderScript = RenderScript.create(context)

    fun gaussianBlur(
        @FloatRange(from = 1.0, to = 25.0)
        radius: Float,
        original: Bitmap
    ): Bitmap {
        val input = Allocation.createFromBitmap(renderScript, original)
        val output = Allocation.createTyped(renderScript, input.type)

        ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript)).apply {
            setRadius(radius)
            setInput(input)
            forEach(output)
        }

        output.copyTo(original)

        return original
    }
}