package com.egoriku.mozaik

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import com.egoriku.ladyhappy.extensions.withStyledAttributes
import com.egoriku.mozaik.calculator.MatrixCalculator
import com.egoriku.mozaik.calculator.MatrixCalculator.Libra
import com.egoriku.mozaik.calculator.MozaikLayoutParamsCalculator
import com.egoriku.mozaik.model.MozaikImageItem
import kotlin.math.roundToInt

class MozaikLayout : RelativeLayout {

    private var photos: List<MozaikImageItem> = mutableListOf()

    private var layoutParamsCalculator: MozaikLayoutParamsCalculator? = null

    private var maxSingleImageHeight = 0
    private var prefImageSize = 0
    private var spacing = 0

    private val density: Float = resources.displayMetrics.density

    private val libra: Libra = object : Libra {
        override fun getWeight(index: Int): Float = photos[index].aspectRatio
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initDimensions(context, attrs)
    }

    private fun initDimensions(context: Context, attrs: AttributeSet?) {
        maxSingleImageHeight = getDisplayHeight(context)

        withStyledAttributes(
                attributeSet = attrs,
                styleArray = R.styleable.MozaikLayout
        ) {
            prefImageSize = getDimension(R.styleable.MozaikLayout_prefImageSize, context.resources.getDimension(R.dimen.pref_image_size)).toInt()
            spacing = getDimensionPixelSize(R.styleable.MozaikLayout_spacing, dpToPx(1f).toInt())
        }
    }

    private fun initCalculator(parentWidth: Int) {
        val matrix = createMatrix(parentWidth)
        layoutParamsCalculator = MozaikLayoutParamsCalculator(matrix, photos, parentWidth, spacing)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)

        if (photos.size == 1) {
            val parent = getChildAt(0)
            val parentParams = getLayoutParamsForSingleImage(photos[0], parent.layoutParams as LayoutParams, parentWidth)
            parent.measure(parentParams.width, parentParams.height)
        } else {
            if (layoutParamsCalculator == null) {
                initCalculator(parentWidth)
            }
            photos.indices.forEach { item ->
                val image = photos[item]
                val parent = getChildAt(item)

                if (parent.visibility == View.GONE) {
                    return@forEach
                }

                if (image.position == null) {
                    image.position = layoutParamsCalculator?.getPostImagePosition(item)
                }

                val position = image.position

                if (position != null) {
                    (parent.layoutParams as LayoutParams).apply {
                        width = position.sizeX
                        height = position.sizeY
                        topMargin = position.marginY
                        leftMargin = position.marginX
                    }

                    parent.measure(position.sizeX, position.sizeY)
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun createMatrix(maxWidth: Int): Array<IntArray> {
        val prefRowCount = getPreferedRowCount(maxWidth)
        val matrixCalculator = MatrixCalculator(photos.size, libra)
        return matrixCalculator.calculate(prefRowCount)
    }

    private fun getPreferedRowCount(maxWidthPx: Int): Int {
        val dpPerProportion = (prefImageSize / density).toInt()
        var proportionDpSum = 0
        for (image in photos) {
            val proportion = image.aspectRatio
            proportionDpSum = (proportionDpSum + proportion * dpPerProportion).toInt()
        }
        val maxContainerWidthDp = convertPxtoDip(maxWidthPx)
        var prefRowCount = (proportionDpSum.toDouble() / maxContainerWidthDp.toDouble()).roundToInt()
        if (prefRowCount == 0) {
            prefRowCount = 1
        }
        return prefRowCount
    }

    private fun convertPxtoDip(pixel: Int): Int {
        val scale = density
        return ((pixel - 0.5f) / scale).toInt()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (photos.size == 1) {
            val parent = getChildAt(0)
            val params = getLayoutParamsForSingleImage(photos[0], parent.layoutParams as LayoutParams, width)
            parent.layout(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin)
        } else {
            if (layoutParamsCalculator == null) {
                initCalculator(width)
            }

            photos.indices.forEach { item ->
                val postImage = photos[item]
                val parent = getChildAt(item)

                if (parent.visibility == View.GONE) {
                    return@forEach
                }

                if (postImage.position == null) {
                    postImage.position = layoutParamsCalculator?.getPostImagePosition(item)
                }

                val position = postImage.position

                if (position != null) {
                    val params = (parent.layoutParams as LayoutParams).apply {
                        width = position.sizeX
                        height = position.sizeY
                        topMargin = position.marginY
                        leftMargin = position.marginX
                    }

                    parent.layout(position.marginX, position.marginY, params.rightMargin, params.bottomMargin)
                }
            }
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    fun setPhotos(photos: List<MozaikImageItem>) {
        this.photos = photos
        this.photos.forEach {
            it.position = null
        }
        layoutParamsCalculator = null
    }

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }

    private fun getLayoutParamsForSingleImage(photo: MozaikImageItem, params: LayoutParams, maxWidth: Int): LayoutParams {
        val coef = photo.width.toDouble() / photo.height.toDouble()
        var measuredwidth = maxWidth
        var measuredheight = (maxWidth / coef).toInt()

        if (maxSingleImageHeight < measuredheight) {
            measuredheight = maxSingleImageHeight
            measuredwidth = (measuredheight * coef).toInt()
        }
        params.height = measuredheight
        params.width = measuredwidth
        return params
    }

    companion object {
        private fun getDisplayHeight(context: Context): Int {
            val display = (context as Activity).windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            return outMetrics.heightPixels
        }
    }
}