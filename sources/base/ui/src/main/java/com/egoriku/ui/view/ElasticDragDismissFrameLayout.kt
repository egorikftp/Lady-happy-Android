package com.egoriku.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import com.egoriku.ladyhappy.extensions.withStyledAttributes
import com.egoriku.ui.R
import kotlin.math.abs
import kotlin.math.log10

class ElasticDragDismissFrameLayout: ConstraintLayout {

    private var dragDismissDistance = Float.MAX_VALUE
    private var dragDismissFraction = -1f
    private var dragDismissScale = 1f
    private var shouldScale = false
    private var dragElacticity = 0.8f
    private var totalDrag = 0f
    private var draggingDown = false
    private var draggingUp = false

    private var lastActionEvent = 0

    private var callback: (() -> Unit)? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        withStyledAttributes(
                attributeSet = attrs,
                styleArray = R.styleable.ElasticDragDismissFrameLayout
        ) {
            dragDismissDistance = getDimensionPixelSize(R.styleable.ElasticDragDismissFrameLayout_dragDismissDistance, 0).toFloat()
            dragDismissFraction = getFloat(R.styleable.ElasticDragDismissFrameLayout_dragDismissFraction, dragDismissFraction)

            dragDismissScale = getFloat(R.styleable.ElasticDragDismissFrameLayout_dragDismissScale, dragDismissScale)
            shouldScale = dragDismissScale != 1f

            dragElacticity = getFloat(R.styleable.ElasticDragDismissFrameLayout_dragElasticity, dragElacticity)
        }
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean =
            nestedScrollAxes and View.SCROLL_AXIS_VERTICAL != 0

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        // if we're in a drag gesture and the user reverses up the we should take those events
        if (draggingDown && dy > 0 || draggingUp && dy < 0) {
            dragScale(dy)
            consumed[1] = dy
        }
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        dragScale(dyUnconsumed)
    }

    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean {
        lastActionEvent = motionEvent.action
        return super.onInterceptTouchEvent(motionEvent)
    }

    override fun onStopNestedScroll(child: View) {
        if (abs(totalDrag) >= dragDismissDistance) {
            dispatchDismissCallback()
        } else {
            if (lastActionEvent == MotionEvent.ACTION_DOWN) {
                translationY = 0f
                scaleX = 1f
                scaleY = 1f
            } else {
                animate()
                        .translationY(0f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200L)
                        .setInterpolator(AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_slow_in))
                        .setListener(null)
                        .start()
            }
            totalDrag = 0f
            draggingUp = false
            draggingDown = draggingUp
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (dragDismissFraction > 0f) {
            dragDismissDistance = h * dragDismissFraction
        }
    }

    fun addListener(listener: (() -> Unit)) {
        callback = listener
    }

    fun removeListener() {
        callback = null;
    }

    private fun dragScale(scroll: Int) {
        if (scroll == 0) return
        totalDrag += scroll.toFloat()

        if (scroll < 0 && !draggingUp && !draggingDown) {
            draggingDown = true
            if (shouldScale) pivotY = height.toFloat()
        } else if (scroll > 0 && !draggingDown && !draggingUp) {
            draggingUp = true
            if (shouldScale) pivotY = 0f
        }

        var dragFraction = log10(1 + (abs(totalDrag) / dragDismissDistance).toDouble()).toFloat()
        var dragTo = dragFraction * dragDismissDistance * dragElacticity
        if (draggingUp) {
            dragTo *= -1f
        }
        translationY = dragTo
        if (shouldScale) {
            val scale = 1 - (1 - dragDismissScale) * dragFraction
            scaleX = scale
            scaleY = scale
        }

        // if we've reversed direction and gone past the settle point then clear the flags to
        // allow the list to get the scroll events & reset any transforms
        if (draggingDown && totalDrag >= 0 || draggingUp && totalDrag <= 0) {
            dragFraction = 0f
            dragTo = dragFraction
            totalDrag = dragTo
            draggingUp = false
            draggingDown = draggingUp
            translationY = 0f
            scaleX = 1f
            scaleY = 1f
        }
    }

    private fun dispatchDismissCallback() {
        callback?.invoke()
    }
}