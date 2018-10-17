package com.egoriku.featureactivitymain.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import androidx.annotation.FloatRange
import android.util.AttributeSet
import android.widget.FrameLayout
import com.egoriku.featureactivitymain.R
import com.egoriku.ui.ktx.colorCompat

class RoundedTopFrameLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val BORDER_RADIUS = 60f
        const val INITIAL_COORDINATE = 0f
    }

    init {
        setWillNotDraw(false)
    }

    private val path by lazy {
        getRoundedTopPath(0f, 0f, width.toFloat(), height.toFloat(), BORDER_RADIUS, BORDER_RADIUS)
    }

    private val backgroundColor = context.colorCompat(R.color.RealWhite)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            canvas.clipPath(path)
            canvas.drawColor(backgroundColor)
        }
    }

    private fun getRoundedTopPath(left: Float, top: Float, right: Float, bottom: Float,
                                  @FloatRange(from = 0.0, to = 100.0) radiusX: Float,
                                  @FloatRange(from = 0.0, to = 100.0) radiusY: Float) = Path().apply {
        val width = right - left
        val height = bottom - top

        val widthMinusCorners = width - 2 * radiusX
        val heightMinusCorners = height - 2 * radiusY

        moveTo(right, top + radiusY)

        //top-right corner
        rQuadTo(INITIAL_COORDINATE, -radiusY, -radiusX, -radiusY)
        rLineTo(-widthMinusCorners, INITIAL_COORDINATE)

        //top-left corner
        rQuadTo(-radiusX, INITIAL_COORDINATE, -radiusX, radiusY)
        rLineTo(INITIAL_COORDINATE, heightMinusCorners)

        rLineTo(INITIAL_COORDINATE, radiusY)
        rLineTo(radiusX, INITIAL_COORDINATE)

        rLineTo(widthMinusCorners, INITIAL_COORDINATE)

        rLineTo(radiusX, INITIAL_COORDINATE)
        rLineTo(INITIAL_COORDINATE, -radiusY)

        rLineTo(INITIAL_COORDINATE, -heightMinusCorners)

        close()
    }
}