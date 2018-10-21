package com.egoriku.photoreportfragment.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import com.egoriku.photoreportfragment.R

@Deprecated("Should use new implementation from github")
class DotsView : View {

    companion object {
        const val ORIENTATION_HORIZONTAL = 0
        const val ORIENTATION_VERTICAL = 1

        @ColorInt
        const val DEFAULT_SELECTED_COLOR = Color.BLUE

        @ColorInt
        const val DEFAULT_UNSELECTED_COLOR = Color.GRAY

        const val DEFAULT_DOT_SPACE = 6f
        const val DEFAULT_DOT_RADIUS = 2f
        const val DEFAULT_SELECTED_DOT_RADIUS = 3f
        const val DEFAULT_DOT_COUNT = 0
        const val DEFAULT_SELECTED_DOT = 0
    }

    private var isInitialized: Boolean = false

    @ColorInt
    private var colorSelected: Int = 0

    @ColorInt
    private var colorUnselected = 0

    private var dotSpace: Int = 0
    private var dotRadius: Int = 0
    private var selectedDotRadius: Int = 0

    private var dotsCount: Int = 0
    private var currentDot: Int = 0
    private var orientation: Int = 0

    private var initialPercentageDot: Int = 0

    private val paint: Paint = Paint().apply {
        isAntiAlias = true
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        context.obtainStyledAttributes(attrs, R.styleable.DotsView).apply {
            colorSelected = getColor(R.styleable.DotsView_colorSelected, DEFAULT_SELECTED_COLOR)
            colorUnselected = getColor(R.styleable.DotsView_colorUnselected, DEFAULT_UNSELECTED_COLOR)

            dotSpace = getDimensionPixelOffset(R.styleable.DotsView_dotSpace, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DOT_SPACE, resources.displayMetrics).toInt())
            dotRadius = getDimensionPixelOffset(R.styleable.DotsView_dotRadius, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DOT_RADIUS, resources.displayMetrics).toInt())
            selectedDotRadius = getDimensionPixelOffset(R.styleable.DotsView_selectedDotRadius, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_SELECTED_DOT_RADIUS, resources.displayMetrics).toInt())

            dotsCount = getInt(R.styleable.DotsView_count, DEFAULT_DOT_COUNT)
            currentDot = getInt(R.styleable.DotsView_selectedDot, DEFAULT_SELECTED_DOT)
            orientation = getInt(R.styleable.DotsView_orientation, ORIENTATION_HORIZONTAL)

            recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0

        when (orientation) {
            ORIENTATION_HORIZONTAL -> {
                height = dotRadius * 2

                if (dotsCount > 0) {
                    width = dotSpace * (dotsCount - 1) + dotRadius * 2 * dotsCount
                }
            }

            ORIENTATION_VERTICAL -> {
                width = dotRadius * 2

                if (dotsCount > 0) {
                    height = dotSpace * (dotsCount - 1) + dotRadius * 2 * dotsCount
                }
            }
        }

        setMeasuredDimension(
                if (modeWidth == View.MeasureSpec.AT_MOST) width + paddingLeft + paddingRight else sizeWidth,
                if (modeHeight == View.MeasureSpec.AT_MOST) height + paddingTop + paddingBottom else sizeHeight
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (dotsCount > 0) {
            when (orientation) {
                ORIENTATION_HORIZONTAL -> {
                    val leftStart = (measuredWidth - (dotSpace * (dotsCount - 1) + dotRadius * 2 * dotsCount)) / 2
                    for (i in 0 until dotsCount) {
                        if (i == currentDot) {
                            paint.color = colorSelected
                            val centerX = leftStart + dotRadius + (dotRadius * 2 + dotSpace) * i
                            val centerY = measuredHeight / 2
                            canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), selectedDotRadius.toFloat(), paint)
                        } else {
                            paint.color = colorUnselected
                            val centerX = leftStart + dotRadius + (dotRadius * 2 + dotSpace) * i
                            val centerY = measuredHeight / 2
                            canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), dotRadius.toFloat(), paint)
                        }
                    }
                }

                ORIENTATION_VERTICAL -> {
                    val topStart = (measuredHeight - (dotSpace * (dotsCount - 1) + dotRadius * 2 * dotsCount)) / 2
                    for (i in 0 until dotsCount) {
                        if (i == currentDot) {
                            paint.color = colorSelected
                            val centerX = measuredWidth / 2
                            val centerY = topStart + dotRadius + (dotRadius * 2 + dotSpace) * i
                            canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), selectedDotRadius.toFloat(), paint)
                        } else {
                            paint.color = colorUnselected
                            val centerX = measuredWidth / 2
                            val centerY = topStart + dotRadius + (dotRadius * 2 + dotSpace) * i
                            canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), dotRadius.toFloat(), paint)
                        }
                    }
                }
            }
        }
    }

    fun updateScrollPosition(percentage: Int) {
        if (!isInitialized) {
            isInitialized = true
            setSelectedDot(DEFAULT_SELECTED_DOT)
            initialPercentageDot = percentage
        } else {
            val newDotPosition = (percentage / initialPercentageDot) - 1
            if (getSelectedDot() != newDotPosition) setSelectedDot(newDotPosition)
        }
    }

    fun getSelectedDot() = currentDot

    fun setSelectedDot(position: Int) {
        currentDot = position
        invalidate()
    }

    fun setColorSelected(colorSelected: Int) {
        this.colorSelected = colorSelected
        invalidate()
    }

    fun setColorUnselected(colorUnselected: Int) {
        this.colorUnselected = colorUnselected
        invalidate()
    }

    fun setDotSpace(dotSpace: Int) {
        this.dotSpace = dotSpace
        invalidate()
    }

    fun setDotRadius(dotRadius: Int) {
        this.dotRadius = dotRadius
        invalidate()
    }

    fun setSelectedDotRadius(dotRadius: Int) {
        this.selectedDotRadius = dotRadius
        invalidate()
    }

    fun setOrientation(orientation: Int) {
        this.orientation = orientation
        requestLayout()
        invalidate()
    }

    fun setDotCount(count: Int) {
        dotsCount = count
        requestLayout()
        invalidate()
    }
}