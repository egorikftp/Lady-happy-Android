package com.egoriku.ladyhappy.detailpage.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.core.feature.DetailPage
import com.egoriku.ladyhappy.detailpage.R
import com.egoriku.ladyhappy.detailpage.databinding.FragmentDetailBinding
import com.egoriku.ladyhappy.detailpage.presentation.adapter.DetailAdapter
import com.egoriku.ladyhappy.extensions.afterMeasured
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.extensions.toast
import com.google.android.material.appbar.AppBarLayout
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.android.ext.android.inject
import kotlin.properties.Delegates

class DetailPageFragment : Fragment(R.layout.fragment_detail), DetailPage {

    private val router: IRouter by inject()

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val url: String by extraNotNull("url")
    private val name: String by extraNotNull("name")

    private var detailAdapter: DetailAdapter by Delegates.notNull()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (enterTransition != null) {
            (enterTransition as Transition).addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)

                    Glide.with(binding.headerBackground)
                            .load(url)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .transform(BlurTransformation(25, 1))
                            .into(binding.headerBackground)
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailAdapter = DetailAdapter()

        with(binding) {
            if (savedInstanceState != null) {
                Glide.with(headerBackground)
                        .load(url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .transform(BlurTransformation(25, 1))
                        .into(headerBackground)
            }

            Glide.with(productImage)
                    .load(url)
                    .transform(CircleCrop())
                    .into(productImage)

            productTitle.text = name
            productTitleToolbar.text = name
            productDescription.text = "Description"

            closeView.setOnClickListener {
                router.back()
            }

            fab.setShowMotionSpecResource(R.animator.animation_fab_show)
            fab.setHideMotionSpecResource(R.animator.animation_fab_hide)

            fab.setOnClickListener {
                toast("Filter click")
            }
            view.afterMeasured {
                fab.show()
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = detailAdapter
            }

            initCoordinatorWithMotion()
        }

        detailAdapter.submitList(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"))
    }

    private fun FragmentDetailBinding.initCoordinatorWithMotion() {
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val scrollPosition = -verticalOffset / appbarLayout.totalScrollRange.toFloat()

            motionLayout.progress = scrollPosition
        }

        appbarLayout.addOnOffsetChangedListener(listener)
    }
}