package com.egoriku.ladyhappy.presentation.adapter.pagination

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import com.egoriku.corelib_kt.dsl.hide
import com.egoriku.corelib_kt.dsl.show
import com.egoriku.ladyhappy.R
import kotlinx.android.synthetic.main.adapter_item_pagination_footer.view.*
import ru.surfstudio.easyadapter.recycler.pagination.BasePaginationableAdapter
import ru.surfstudio.easyadapter.recycler.pagination.PaginationState

class PaginationableAdapter<H : RecyclerView.ViewHolder> : BasePaginationableAdapter() {

    private var paginationFooterItemController: BasePaginationFooterController<*>? = null

    override fun getPaginationFooterController(): BasePaginationFooterController<*> {
        if (paginationFooterItemController == null) {
            paginationFooterItemController = PaginationFooterItemController()
        }

        return paginationFooterController
    }

    private class PaginationFooterItemController : BasePaginationFooterController<PaginationFooterItemController.Holder>() {
        override fun createViewHolder(parent: ViewGroup, listener: OnShowMoreListener) = Holder(parent, listener)

        inner class Holder(parent: ViewGroup, listener: OnShowMoreListener) : BasePaginationFooterHolder(parent, R.layout.adapter_item_pagination_footer) {

            private val progressBar: ProgressBar
            private val loadMore: TextView

            init {
                progressBar = itemView.paginationProgressView.apply {
                    hide()
                }
                loadMore = itemView.paginationTextView.apply {
                    setOnClickListener { _ -> listener.onLoadMore() }
                    hide()
                }
            }

            override fun bind(state: PaginationState) {
                when (state) {
                    PaginationState.READY -> {
                        progressBar.show()
                        loadMore.hide()
                    }
                    PaginationState.COMPLETE -> {
                        progressBar.hide()
                        loadMore.hide()
                    }
                    PaginationState.ERROR -> {
                        progressBar.hide()
                        loadMore.show()
                    }
                    else -> throw  IllegalArgumentException("Unsupported pagination state: $state")
                }
            }

            override fun animateRemove(): Boolean {
                itemView.animate()
                        .alpha(0f)
                        .setInterpolator(AccelerateInterpolator())
                        .setListener(object : AnimatorListenerAdapter() {

                            override fun onAnimationEnd(animation: Animator?) {
                                itemView.alpha = 1f
                            }

                            override fun onAnimationCancel(animation: Animator?) {
                                itemView.alpha = 1f
                            }
                        })
                        .start()
                return false
            }
        }
    }
}