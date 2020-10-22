package com.egoriku.landing.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.core.feature.AboutUsFeature
import com.egoriku.extensions.browseUrl
import com.egoriku.landing.R
import com.egoriku.landing.common.parallax.ParallaxScrollListener
import com.egoriku.landing.databinding.FragmentLandingBinding
import com.egoriku.landing.presentation.controller.*
import com.egoriku.ladyhappy.ui.controller.NoDataController
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class LandingPageFragment : Fragment(R.layout.fragment_landing), AboutUsFeature {

    private val landingViewModel: LandingViewModel by lifecycleScope.viewModel(this)

    private val binding by viewBinding(FragmentLandingBinding::bind)

    private var parallaxScrollListener: ParallaxScrollListener? = null

    private val landingAdapter = EasyAdapter()

    private lateinit var headerController: HeaderController
    private lateinit var noDataController: NoDataController
    private lateinit var aboutController: AboutController
    private lateinit var quotesController: QuotesController
    private lateinit var ourTeamController: OurTeamController
    private lateinit var sectionsHeaderController: SectionsHeaderController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        landingViewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }

        initViews()
    }

    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = landingAdapter
        }

        parallaxScrollListener = ParallaxScrollListener().apply {
            initWith(binding.recyclerView, lifecycle)
        }

        headerController = HeaderController()
        noDataController = NoDataController {
            landingViewModel.retryLoading()
        }

        aboutController = AboutController()
        sectionsHeaderController = SectionsHeaderController()
        quotesController = QuotesController(parallaxScrollListener)
        ourTeamController = OurTeamController(parallaxScrollListener) {
            browseUrl(it)
        }
    }

    private fun render(screenModel: LandingScreenModel) {
        val itemList = ItemList.create()

        when (screenModel.loadState) {
            LoadState.PROGRESS -> showProgress()
            else -> hideProgress()
        }

        itemList.addIf(screenModel.isEmpty() && screenModel.loadState == LoadState.ERROR_LOADING, noDataController)

        screenModel.landingModel?.let {
            itemList.add(headerController)
                    .add(it.aboutInfo, aboutController)
                    .addIf(it.quotes.isNotEmpty(), R.string.adapter_item_header_quotes, sectionsHeaderController)
                    .addIf(it.quotes.isNotEmpty(), it.quotes, quotesController)
                    .addIf(it.teamMembers.isNotEmpty(), R.string.adapter_item_header_our_team, sectionsHeaderController)
                    .addAll(it.teamMembers, ourTeamController)
        }

        landingAdapter.setItems(itemList)
    }

    private fun showProgress() = with(binding.hatsProgressAnimationView) {
        startAnimation()
        visible()
    }

    private fun hideProgress() = with(binding.hatsProgressAnimationView) {
        stopAnimation()
        gone()
    }

    override fun onDestroy() {
        super.onDestroy()
        parallaxScrollListener = null
    }
}