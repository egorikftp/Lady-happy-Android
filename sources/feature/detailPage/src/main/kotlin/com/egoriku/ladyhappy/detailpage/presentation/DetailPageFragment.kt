package com.egoriku.ladyhappy.detailpage.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.core.feature.DetailPage
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_DETAIL_PAGE_EXTRA
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.R
import com.egoriku.ladyhappy.detailpage.databinding.FragmentDetailBinding
import com.egoriku.ladyhappy.detailpage.presentation.adapter.DetailAdapter
import com.egoriku.ladyhappy.detailpage.presentation.adapter.LoadingStateFooterAdapter
import com.egoriku.ladyhappy.detailpage.presentation.viewmodel.DetailViewModel
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.ladyhappy.glide.transformations.GradientOverlayTransformation
import com.google.android.material.appbar.AppBarLayout
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPageFragment : ScopeFragment(R.layout.fragment_detail), DetailPage {

    private val router: IRouter by inject()

    private val viewModel by viewModel<DetailViewModel>()

    private val viewBinding by viewBinding(FragmentDetailBinding::bind)

    private val detailPageParams: DetailPageParams by extraNotNull(KEY_DETAIL_PAGE_EXTRA)

    private var detailAdapter = DetailAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (enterTransition != null) {
            (enterTransition as Transition).addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                    viewBinding.loadBackgroundHeader()
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            viewBinding.loadBackgroundHeader()
        }

        with(viewBinding) {
            initPredefinedData()
            initRecyclerView()
            initAppBarScrollListener()

            fab.setOnClickListener {
                toast("Filter click")
            }

            closeView.setOnClickListener {
                router.back()
            }
        }

        lifecycleScope.launch {
            viewModel.getDetailFlow(detailParams = detailPageParams).collect {
                detailAdapter.submitData(it)
            }
        }
    }

    private fun FragmentDetailBinding.initRecyclerView() {
        detailAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && detailAdapter.itemCount < 1
            ) {
                fab.hide()
                bottomAppBar.performHide()

                placeholderContainer.emptyStateImage.visible()
                placeholderContainer.emptyStateMessage.visible()
            } else {
                fab.show()
                bottomAppBar.performShow()
            }

            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            placeholderContainer.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            placeholderContainer.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.source.refresh as? LoadState.Error
            errorState?.let {
                toast(getString(R.string.detail_page_error_loading, it.error.toString()))

                fab.hide()
                bottomAppBar.performHide()
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailAdapter.withLoadStateFooter(
                    footer = LoadingStateFooterAdapter(detailAdapter::retry)
            )
        }
    }

    private fun FragmentDetailBinding.initPredefinedData() {
        Glide.with(productImage)
                .load(detailPageParams.productLogoUrl)
                .transform(CircleCrop())
                .into(productImage)

        productTitle.text = detailPageParams.productName
        productTitleToolbar.text = detailPageParams.productName
        productDescription.text = detailPageParams.productDescription
    }

    private fun FragmentDetailBinding.loadBackgroundHeader() {
        Glide.with(headerBackground)
                .load(detailPageParams.productLogoUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(
                        BlurTransformation(25, 1),
                        GradientOverlayTransformation(
                                startColor = colorCompat(R.color.RealBlack0),
                                centerColor = colorCompat(R.color.RealBlack30),
                                endColor = colorCompat(R.color.RealBlack)
                        )
                ).into(headerBackground)
    }

    private fun FragmentDetailBinding.initAppBarScrollListener() {
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val scrollPosition = -verticalOffset / appbarLayout.totalScrollRange.toFloat()

            motionLayout.progress = scrollPosition

            placeholderContainer.root.updateLayoutParams<ConstraintLayout.LayoutParams> {
                verticalBias = 0.3f * scrollPosition + 0.2f
            }
        }

        appbarLayout.addOnOffsetChangedListener(listener)
    }
}