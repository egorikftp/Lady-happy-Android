package com.egoriku.featureactivitymain.presentation.activity.drawable

import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable

class RoundTopRectDrawable(backgroundColor: ColorStateList?, private var radius: Float) : Drawable() {

    companion object {
        private val COS_45 = Math.cos(Math.toRadians(45.0))
    }

    private val paint: Paint
    private val boundsRectF: RectF
    private val boundsRectI: Rect
    private var padding: Float = 0.toFloat()

    private var insetForPadding = false
    private var iInsetForRadius = true

    private var background: ColorStateList? = null
    private var tintFilter: PorterDuffColorFilter? = null
    private var tint: ColorStateList? = null
    private var tintMode: PorterDuff.Mode? = null

    var color: ColorStateList?
        get() = background
        set(color) {
            setBackground(color)
            invalidateSelf()
        }

    init {
        tintMode = PorterDuff.Mode.SRC_IN
        paint = Paint(5)
        setBackground(backgroundColor)
        boundsRectF = RectF()
        boundsRectI = Rect()
    }

    private fun setBackground(color: ColorStateList?) {
        background = color ?: ColorStateList.valueOf(0)
        paint.color = background!!.getColorForState(state, background!!.defaultColor)
    }

    override fun draw(canvas: Canvas) {
        val paint = this.paint
        val clearColorFilter: Boolean
        if (tintFilter != null && paint.colorFilter == null) {
            paint.colorFilter = tintFilter
            clearColorFilter = true
        } else {
            clearColorFilter = false
        }

        canvas.drawRoundRect(boundsRectF, radius, 0f, paint)
        if (clearColorFilter) {
            paint.colorFilter = null
        }
    }

    private fun updateBounds(newBounds: Rect?) {
        var bounds = newBounds

        if (bounds == null) {
            bounds = getBounds()
        }

        if (bounds == null) return

        boundsRectF.set(bounds.left.toFloat(), bounds.top.toFloat(), bounds.right.toFloat(), bounds.bottom.toFloat())
        boundsRectI.set(bounds)

        if (insetForPadding) {
            val verticalInset = calculateVerticalPadding(padding, radius, iInsetForRadius)
            val horizontalInset = calculateHorizontalPadding(padding, radius, iInsetForRadius)

            boundsRectI.inset(
                    Math.ceil(horizontalInset.toDouble()).toInt(),
                    Math.ceil(verticalInset.toDouble()).toInt()
            )

            boundsRectF.set(boundsRectI)
        }
    }

    private fun calculateVerticalPadding(maxShadowSize: Float, cornerRadius: Float, addPaddingForCorners: Boolean): Float {
        return if (addPaddingForCorners) ((maxShadowSize * 1.5f).toDouble() + (1.0 - COS_45) * cornerRadius.toDouble()).toFloat() else maxShadowSize * 1.5f
    }

    private fun calculateHorizontalPadding(maxShadowSize: Float, cornerRadius: Float, addPaddingForCorners: Boolean): Float {
        return if (addPaddingForCorners) (maxShadowSize.toDouble() + (1.0 - COS_45) * cornerRadius.toDouble()).toFloat() else maxShadowSize
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        updateBounds(bounds)
    }

    override fun getOutline(outline: Outline) = outline.setRoundRect(boundsRectI, radius)

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setTintList(tint: ColorStateList?) {
        this.tint = tint
        tintFilter = createTintFilter(this.tint, tintMode)
        invalidateSelf()
    }

    override fun setTintMode(tintMode: PorterDuff.Mode) {
        this.tintMode = tintMode
        tintFilter = createTintFilter(tint, this.tintMode)
        invalidateSelf()
    }

    override fun onStateChange(stateSet: IntArray): Boolean {
        val newColor = background!!.getColorForState(stateSet, background!!.defaultColor)
        val colorChanged = newColor != paint.color
        if (colorChanged) {
            paint.color = newColor
        }

        return if (tint != null && tintMode != null) {
            tintFilter = createTintFilter(tint, tintMode)
            true
        } else {
            colorChanged
        }
    }

    override fun isStateful(): Boolean {
        return tint != null && tint!!.isStateful || background != null && background!!.isStateful || super.isStateful()
    }

    private fun createTintFilter(tint: ColorStateList?, tintMode: PorterDuff.Mode?): PorterDuffColorFilter? {
        return if (tint != null && tintMode != null) {
            val color = tint.getColorForState(state, 0)
            PorterDuffColorFilter(color, tintMode)
        } else {
            null
        }
    }
}