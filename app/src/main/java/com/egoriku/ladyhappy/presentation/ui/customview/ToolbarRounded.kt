package com.egoriku.ladyhappy.presentation.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.egoriku.corelib_kt.extensions.colorCompat
import com.egoriku.ladyhappy.R

class ToolbarRounded @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var scale: Float = 0f
    private val arcSize = 70f
    private val extendOverBoundary = 10f
    private val ovalPaint = Paint().apply {
        color = colorCompat(context, R.color.white)
    }

    fun setScale(scale: Float) {
        this.scale = if (scale < 0) {
            0f
        } else {
            scale
        }

        invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(
                -extendOverBoundary,
                height - arcSize * scale,
                width + extendOverBoundary,
                height + arcSize * scale,
                ovalPaint)
    }

}