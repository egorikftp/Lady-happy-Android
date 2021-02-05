package com.egoriku.ladyhappy.detailpage.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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
import com.egoriku.ladyhappy.detailpage.domain.model.DetailModel
import com.egoriku.ladyhappy.detailpage.presentation.adapter.DetailAdapter
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.google.android.material.appbar.AppBarLayout
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.properties.Delegates

class DetailPageFragment : Fragment(R.layout.fragment_detail), DetailPage {

    private val router: IRouter by inject()

    private val viewBinding by viewBinding(FragmentDetailBinding::bind)

    private val detailPageParams: DetailPageParams by extraNotNull(KEY_DETAIL_PAGE_EXTRA)

    private var detailAdapter: DetailAdapter by Delegates.notNull()

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
    }

    private fun FragmentDetailBinding.initRecyclerView() {
        detailAdapter = DetailAdapter()

        // TODO: 2/5/21 Use real data
        lifecycleScope.launch {
            delay(2000)
            detailAdapter.submitList(
                    listOf(
                            DetailModel(
                                    images = listOf(
                                            MozaikItem(1200, 800, "https://firebasestorage.googleapis.com/v0/b/lady-happy.appspot.com/o/subcategories%2F1.2%2FIMG_0207_2018.09.06_22-49%20(1).jpg?alt=media&token=b29b83cb-f2e8-4512-bd8a-b5cc120bd179"),
                                            MozaikItem(1200, 800, "https://firebasestorage.googleapis.com/v0/b/lady-happy.appspot.com/o/subcategories%2F1.2%2FIMG_0207_2018.09.06_22-49%20(1).jpg?alt=media&token=b29b83cb-f2e8-4512-bd8a-b5cc120bd179")
                                    ),
                                    description = "1",
                                    date = "22 June 2020"
                            )
                    ))

            fab.show()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailAdapter
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
                .transform(BlurTransformation(25, 1))
                .into(headerBackground)
    }

    private fun FragmentDetailBinding.initAppBarScrollListener() {
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val scrollPosition = -verticalOffset / appbarLayout.totalScrollRange.toFloat()

            motionLayout.progress = scrollPosition
        }

        appbarLayout.addOnOffsetChangedListener(listener)
    }
}