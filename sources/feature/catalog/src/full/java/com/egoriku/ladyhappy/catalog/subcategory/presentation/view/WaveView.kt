package com.egoriku.ladyhappy.catalog.subcategory.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.egoriku.ladyhappy.catalog.R
import kotlin.math.sin

class WaveView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * xValue, y1Value, y2Value are used to plot the path for wave
     */
    private var xValue = 0f
    private var y1Value = 0f
    private var y2Value = 0f

    private var quadrant = 30

    private val wavePaint: Paint
    private val path = Path()

    /**
     * @frequency - Then less the frequency more will be the number of waves
     */
    private val frequency = 50

    /**
     * @amplitude - Amplitude gives the height of wave
     */
    private val amplitude = 10


    init {
        var waveColor: Int = Color.BLUE

        context.withStyledAttributes(
                set = attrs,
                attrs = R.styleable.WaveView
        ) {
            waveColor = getColor(R.styleable.WaveView_waveFillColor, Color.BLUE)
        }

        wavePaint = Paint().apply {
            isAntiAlias = true
            strokeWidth = 2f
            color = waveColor
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path.moveTo(0f, height.toFloat())
        path.lineTo(0f, quadrant.toFloat())

        var i = 0

        while (i < width + 10) {
            xValue = i.toFloat()
            y1Value = quadrant + amplitude * sin(x = (i + 10) * Math.PI / frequency).toFloat()
            y2Value = quadrant * 2 + amplitude * sin(x = (i + 10) * Math.PI / frequency).toFloat()
            path.lineTo(xValue, y1Value)
            i += 10
        }

        path.lineTo(width.toFloat(), height.toFloat())

        canvas.drawPath(path, wavePaint)
    }
}