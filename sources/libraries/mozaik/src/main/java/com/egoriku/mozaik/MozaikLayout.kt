package com.egoriku.mozaik

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.egoriku.extensions.pxToDp
import com.egoriku.mozaik.model.MozaikItem
import com.egoriku.mozaik.strategy.StrategyResolver
import com.egoriku.mozaik.strategy.internal.model.Rect
import com.egoriku.mozaik.strategy.internal.model.StrategyData

class MozaikLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    init {
        setWillNotDraw(false)
    }

    private val strategyData = StrategyData(dividerSize = pxToDp(20))

    var onViewReady: ((view: ImageView, url: String) -> Unit)? = null

    var dividerSize: Int = 0
        set(value) {
            field = value

            strategyData.dividerSize = value

            requestLayout()
            invalidate()
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
                    MeasureSpec.makeMeasureSpec(rect.width(), MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(rect.height(), MeasureSpec.UNSPECIFIED)
            )
        }

        setMeasuredDimension(parentWidth, strategyData.parentHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (childCount != 0) {
            for (i in 0 until childCount) {
                val rect = strategyData.rect[i]

                getChildAt(i).layout(
                        rect.left + paddingLeft,
                        rect.top + paddingTop,
                        rect.right + paddingLeft,
                        rect.bottom + paddingTop
                )
            }
        }

        for (i in 0 until childCount) {
            val url = strategyData.mozaikItems[i].url

            if (url.isNotEmpty()) {
                onViewReady?.invoke(getChildAt(i) as ImageView, url)
            }
        }
    }

    fun setItems(items: List<MozaikItem>) {
        removeAllViews()

        items.forEachIndexed { _, _ ->
            addView(ImageView(context))
        }

        strategyData.rect = List(items.size) { Rect() }
        strategyData.mozaikItems = items

        requestLayout()
        invalidate()
    }
}