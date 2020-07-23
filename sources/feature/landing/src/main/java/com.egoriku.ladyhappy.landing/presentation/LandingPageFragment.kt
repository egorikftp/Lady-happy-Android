package com.egoriku.ladyhappy.landing.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.di.findDependencies
import com.egoriku.ladyhappy.arch.fragment.BaseInjectableFragment
import com.egoriku.ladyhappy.extensions.browseUrl
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.injectViewModel
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.ladyhappy.landing.R
import com.egoriku.ladyhappy.landing.common.parallax.ParallaxScrollListener
import com.egoriku.ladyhappy.landing.databinding.FragmentLandingBinding
import com.egoriku.ladyhappy.landing.di.LandingFragmentComponent
import com.egoriku.ladyhappy.landing.presentation.controller.*
import com.egoriku.ui.controller.NoDataController
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

class LandingPageFragment : BaseInjectableFragment(R.layout.fragment_landing) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var landingViewModel: LandingViewModel
    private val binding: FragmentLandingBinding by viewBinding()

    private var parallaxScrollListener: ParallaxScrollListener? = null

    private val landingAdapter = EasyAdapter().apply {
        isFirstInvisibleItemEnabled = false
    }

    private lateinit var headerController: HeaderController
    private lateinit var noDataController: NoDataController
    private lateinit var aboutController: AboutController
    private lateinit var quotesController: QuotesController
    private lateinit var ourTeamController: OurTeamController
    private lateinit var sectionsHeaderController: SectionsHeaderController

    override fun injectDependencies() = LandingFragmentComponent.init(findDependencies()).inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        landingViewModel = injectViewModel(viewModelFactory)

        landingViewModel.screenState.observe(viewLifecycleOwner, Observer {
            render(it)
        })

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