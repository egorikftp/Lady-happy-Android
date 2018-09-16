package com.egoriku.featureactivitymain.presentation.activity.drawable

import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable


class RoundTopRectDrawable(backgroundColor: ColorStateList?, private var radius: Float) : Drawable() {

    private val paint: Paint
    private val boundsRectF: RectF
    private val boundsRectI: Rect

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

        val path = RoundedRect(
                boundsRectF.left,
                boundsRectF.top,
                boundsRectF.right,
                boundsRectF.bottom,
                radius, radius,
                true,
                true,
                false,
                false
        )

        canvas.drawPath(path, paint)

       // canvas.drawRoundRect(boundsRectF, radius, radius, paint)
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
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        updateBounds(bounds)
    }

    override fun getOutline(outline: Outline) {
        val path = RoundedRect(
                boundsRectF.left,
                boundsRectF.top,
                boundsRectF.right,
                boundsRectF.bottom,
                radius, radius,
                true,
                true,
                false,
                false
        )

        outline.setConvexPath(path)
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