package com.egoriku.ladyhappy.landing.presentation.controller

import android.graphics.Matrix
import android.view.ViewGroup
import com.egoriku.extensions.inflater
import com.egoriku.landing.R
import com.egoriku.ladyhappy.landing.common.parallax.ParallaxScrollListener
import com.egoriku.ladyhappy.landing.common.parallax.ParallaxScrollStateListener
import com.egoriku.landing.databinding.AdapterItemQuotesBinding
import com.egoriku.ladyhappy.landing.domain.model.QuotesModel
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import java.util.*
import kotlin.math.max
import kotlin.math.min

internal class QuotesController(
        private val parallaxScrollListener: ParallaxScrollListener?
) : BindableItemController<List<QuotesModel>, QuotesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemQuotesBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: List<QuotesModel>) = data.hashCode().toString()

    inner class Holder(
            private val itemBinding: AdapterItemQuotesBinding
    ) : BindableViewHolder<List<QuotesModel>>(itemBinding.root),
            ParallaxScrollStateListener {

        private val random = Random()
        private var itemHeight = itemView.resources.getDimension(R.dimen.adapter_item_quotes_height).toInt()

        private var quotesList = emptyList<QuotesModel>()

        init {
            parallaxScrollListener?.apply {
                addListener(this@Holder)
                if (recyclerViewHeight > 0 && itemBinding.quotesBackground.drawable != null) {
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
                    itemBinding.quotesContent.text = this.quote
                    itemBinding.quotesAuthor.text = this.author
                }
            }
        }

        override fun bind(data: List<QuotesModel>) {
            quotesList = data
            updateQuote()
        }

        private fun processScroll() {
            parallaxScrollListener?.let {
                val recyclerViewHeight = it.recyclerViewHeight + itemHeight

                val min = min(itemView.top + itemView.translationY.toInt() + itemHeight, recyclerViewHeight)
                val intrinsicHeightDrawable = itemBinding.quotesBackground.drawable.intrinsicHeight
                val max = (1.0f - max(0, min) * 1.0f / recyclerViewHeight) *
                        (-(intrinsicHeightDrawable - itemHeight)).toFloat()

                val rectDrawable = itemBinding.quotesBackground.drawable.bounds
                val leftOffset = (itemBinding.quotesBackground.measuredWidth - rectDrawable.width()) / 2f

                val matrix = Matrix()
                matrix.setTranslate(0.0f, max)
                matrix.postTranslate(leftOffset, 0f)

                itemBinding.quotesBackground.imageMatrix = matrix
            }
        }

        override fun onScroll() {
            if (itemBinding.quotesBackground.drawable != null) {
                processScroll()
            }
        }

        override fun onAttach() {
            if (itemBinding.quotesBackground.drawable != null) {
                processScroll()
            }
        }

        override fun onDetach() = Unit
    }
}
