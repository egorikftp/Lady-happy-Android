package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.graphics.Matrix
import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.parallax.IParallaxScrollListener
import com.egoriku.ladyhappy.common.parallax.ParallaxScrollListener
import kotlinx.android.synthetic.main.adapter_item_quotes.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class QuotesController(val scrollListener: ParallaxScrollListener) : BindableItemController<String, QuotesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_quotes),
            IParallaxScrollListener {

        private val quotaContent = itemView.quotesContent
        private val quotaAuthor = itemView.quotesAuthor
        private val imageView = itemView.quotesBackground

        private var parallaxScrollListener: ParallaxScrollListener? = scrollListener
        private var itemHeight = itemView.resources.getDimension(R.dimen.adapter_item_quotes_height).toInt()

        init {
            parallaxScrollListener?.apply {
                addListener(this@Holder)
                if (recyclerViewHeight > 0 && imageView.drawable != null) {
                    processScroll()
                }
            }
        }

        override fun bind(data: String) {
            quotaContent.text = data
            quotaAuthor.text = "Ив Сен Лоран"
        }

        private fun processScroll() {
            parallaxScrollListener?.let {
                val recyclerViewHeight = it.recyclerViewHeight + itemHeight

                val min = Math.min(itemView.top + itemView.translationY.toInt() + itemHeight, recyclerViewHeight)
                val intrinsicHeightDrawable = imageView.drawable.intrinsicHeight
                val max = (1.0f - Math.max(0, min) * 1.0f / recyclerViewHeight) * (-(intrinsicHeightDrawable - itemHeight)).toFloat()

                val rectDrawable = imageView.drawable.bounds
                val leftOffset = (imageView.measuredWidth - rectDrawable.width()) / 2f

                val matrix = Matrix()
                matrix.setTranslate(0.0f, max)
                matrix.postTranslate(leftOffset, 0f)

                imageView.imageMatrix = matrix
            }
        }

        override fun onScroll() {
            if (imageView.drawable != null) {
                processScroll()
            }
        }

        override fun onAttach() {
            if (imageView.drawable != null) {
                processScroll()
            }
        }

        override fun onDetach() {
            parallaxScrollListener = null
        }
    }
}
