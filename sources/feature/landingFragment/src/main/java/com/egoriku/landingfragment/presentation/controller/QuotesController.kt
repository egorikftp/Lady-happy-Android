package com.egoriku.landingfragment.presentation.controller

import android.graphics.Matrix
import android.view.View
import android.view.ViewGroup
import com.egoriku.core.model.IQuotesModel
import com.egoriku.landingfragment.R
import com.egoriku.landingfragment.common.parallax.IParallaxScrollListener
import com.egoriku.landingfragment.common.parallax.ParallaxScrollListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_quotes.*
import kotlinx.android.synthetic.main.adapter_item_quotes.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import java.util.*

internal class QuotesController(val scrollListener: ParallaxScrollListener) : BindableItemController<List<IQuotesModel>, QuotesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: List<IQuotesModel>) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<List<IQuotesModel>>(parent, R.layout.adapter_item_quotes),
            IParallaxScrollListener, LayoutContainer {

        override val containerView: View?
            get() = itemView

        private var parallaxScrollListener: ParallaxScrollListener? = scrollListener
        private val random = Random()
        private var itemHeight = itemView.resources.getDimension(R.dimen.adapter_item_quotes_height).toInt()

        private var quotesList = emptyList<IQuotesModel>()

        init {
            parallaxScrollListener?.apply {
                addListener(this@Holder)
                if (recyclerViewHeight > 0 && itemView.quotesBackground.drawable != null) {
                    processScroll()
                }
            }

            itemView.setOnClickListener {
                updateQuote()
            }
        }

        private fun updateQuote() {
            if (quotesList.isNotEmpty()) {
                with(quotesList[random.nextInt(quotesList.size)]) {
                    quotesContent.text = this.quote
                    quotesAuthor.text = this.author
                }
            }
        }

        override fun bind(data: List<IQuotesModel>) {
            quotesList = data
            updateQuote()
        }

        private fun processScroll() {
            parallaxScrollListener?.let {
                val recyclerViewHeight = it.recyclerViewHeight + itemHeight

                val min = Math.min(itemView.top + itemView.translationY.toInt() + itemHeight, recyclerViewHeight)
                val intrinsicHeightDrawable = quotesBackground.drawable.intrinsicHeight
                val max = (1.0f - Math.max(0, min) * 1.0f / recyclerViewHeight) * (-(intrinsicHeightDrawable - itemHeight)).toFloat()

                val rectDrawable = quotesBackground.drawable.bounds
                val leftOffset = (quotesBackground.measuredWidth - rectDrawable.width()) / 2f

                val matrix = Matrix()
                matrix.setTranslate(0.0f, max)
                matrix.postTranslate(leftOffset, 0f)

                quotesBackground.imageMatrix = matrix
            }
        }

        override fun onScroll() {
            if (quotesBackground.drawable != null) {
                processScroll()
            }
        }

        override fun onAttach() {
            if (quotesBackground.drawable != null) {
                processScroll()
            }
        }

        override fun onDetach() {
            parallaxScrollListener = null
        }
    }
}
