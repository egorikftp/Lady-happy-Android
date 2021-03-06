package com.egoriku.ladyhappy.mozaik

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.egoriku.ladyhappy.extensions.pxToDp
import com.egoriku.ladyhappy.extensions.toImageView
import com.egoriku.ladyhappy.extensions.withStyledAttributes
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.mozaik.strategy.StrategyResolver
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.Rect
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.StrategyData

private const val DIVIDER_SIZE = 15

fun interface OnItemClick {

    fun onClick(position: Int, mozaikItems: List<MozaikItem>, transitionView: ImageView)
}

class MozaikLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val strategyData = StrategyData(dividerSize = pxToDp(DIVIDER_SIZE))

    var isMozaikClickable: Boolean = true
    var placeholderColor: Int = -1

    var onViewReady: ((view: ImageView, url: String) -> Unit)? = null
    var onItemClick: OnItemClick? = null

    init {
        setWillNotDraw(false)

        withStyledAttributes(
                attributeSet = attrs,
                styleArray = R.styleable.MozaikLayout
        ) {
            isMozaikClickable = getBoolean(R.styleable.MozaikLayout_isMozaikClickable, true)
            placeholderColor = getColor(R.styleable.MozaikLayout_placeholderColor, -1)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)

        strategyData.parentWidth = parentWidth

        StrategyResolver
                .resolveBySize(childCount)
                .calculateWith(strategyData)

        for (i in 0 until childCount) {
            val childAt = getChildAt(i)
            val rect = strategyData.rect[i]

            childAt.measure(
                    MeasureSpec.makeMeasureSpec(rect.width(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(rect.height(), MeasureSpec.EXACTLY)
            )
        }

        setMeasuredDimension(parentWidth, strategyData.parentHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (childCount != 0) {
            for (i in 0 until childCount) {
                val rect = strategyData.rect[i]

                val childImageView = getChildAt(i).toImageView()

                childImageView.layout(
                        rect.left + paddingLeft,
                        rect.top + paddingTop,
                        rect.right + paddingLeft,
                        rect.bottom + paddingTop
                )

                val url = strategyData.mozaikItems[i].url

                if (url.isNotEmpty()) {
                    onViewReady?.invoke(childImageView, url)
                }

                if (isMozaikClickable) {
                    childImageView.setOnClickListener {
                        onItemClick?.onClick(
                                position = i,
                                mozaikItems = strategyData.mozaikItems,
                                transitionView = it.toImageView()
                        )
                    }
                }
            }
        }
    }

    fun setItems(items: List<MozaikItem>) {
        removeAllViews()

        items.forEachIndexed { _, _ ->
            addView(ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP

                if (placeholderColor != -1) {
                    setBackgroundColor(placeholderColor)
                }
            })
        }

        strategyData.rect = List(items.size) { Rect() }
        strategyData.mozaikItems = items

        requestLayout()
        invalidate()
    }

    fun getItemByPosition(position: Int) = getChildAt(position).toImageView()
}