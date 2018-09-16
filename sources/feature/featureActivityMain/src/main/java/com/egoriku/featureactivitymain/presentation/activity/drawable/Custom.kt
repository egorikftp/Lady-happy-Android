package com.egoriku.featureactivitymain.presentation.activity.drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.FrameLayout
import com.egoriku.featureactivitymain.R

class Custom @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        setWillNotDraw(false)
        background = ContextCompat.getDrawable(context, R.color.Terracota)
    }

    val path by lazy { RoundedRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            60f,
            60f,
            true,
            true,
            false,
            false)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.clipPath(path)
    }

    fun RoundedRect(
            left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float,
            tl: Boolean, tr: Boolean, br: Boolean, bl: Boolean
    ): Path {
        var rx = rx
        var ry = ry
        val path = Path()
        if (rx < 0) rx = 0f
        if (ry < 0) ry = 0f
        val width = right - left
        val height = bottom - top
        if (rx > width / 2) rx = width / 2
        if (ry > height / 2) ry = height / 2
        val widthMinusCorners = width - 2 * rx
        val heightMinusCorners = height - 2 * ry

        path.moveTo(right, top + ry)
        if (tr)
            path.rQuadTo(0F, -ry, -rx, -ry)//top-right corner
        else {
            path.rLineTo(0F, -ry)
            path.rLineTo(-rx, 0F)
        }
        path.rLineTo(-widthMinusCorners, 0F)
        if (tl)
            path.rQuadTo(-rx, 0F, -rx, ry) //top-left corner
        else {
            path.rLineTo(-rx, 0F)
            path.rLineTo(0F, ry)
        }
        path.rLineTo(0F, heightMinusCorners)

        if (bl)
            path.rQuadTo(0F, ry, rx, ry)//bottom-left corner
        else {
            path.rLineTo(0F, ry)
            path.rLineTo(rx, 0F)
        }

        path.rLineTo(widthMinusCorners, 0F)
        if (br)
            path.rQuadTo(rx, 0F, rx, -ry) //bottom-right corner
        else {
            path.rLineTo(rx, 0F)
            path.rLineTo(0F, -ry)
        }

        path.rLineTo(0F, -heightMinusCorners)

        path.close()//Given close, last lineto can be removed.

        return path
    }
}