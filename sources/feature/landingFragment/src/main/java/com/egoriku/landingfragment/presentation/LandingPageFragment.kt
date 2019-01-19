package com.egoriku.landingfragment.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.egoriku.core.actions.common.IMainActivityConnector
import com.egoriku.core.di.findDependencies
import com.egoriku.landingfragment.R
import com.egoriku.landingfragment.common.parallax.ParallaxScrollListener
import com.egoriku.landingfragment.di.LandingFragmentComponent
import com.egoriku.landingfragment.presentation.controller.*
import com.egoriku.ui.arch.fragment.BaseInjectableFragment
import com.egoriku.ui.controller.NoDataController
import com.egoriku.ui.dsl.simpleOnScrollListener
import com.egoriku.ui.ktx.browseUrl
import com.egoriku.ui.ktx.gone
import com.egoriku.ui.ktx.show
import kotlinx.android.synthetic.main.fragment_landing.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

internal class LandingPageFragment : BaseInjectableFragment<LandingPageContract.View, LandingPageContract.Presenter>(), LandingPageContract.View {

    companion object {
        fun newInstance() = LandingPageFragment()
    }

    @Inject
    lateinit var landingPagePresenter: LandingPageContract.Presenter

    private var mainActivityConnector: IMainActivityConnector? = null
    private var parallaxScrollListener: ParallaxScrollListener? = null

    private val landingAdapter = EasyAdapter().apply {
        setFirstInvisibleItemEnabled(false)
    }

    private lateinit var headerController: HeaderController
    private lateinit var noDataController: NoDataController
    private lateinit var aboutController: AboutController
    private lateinit var quotesController: QuotesController
    private lateinit var ourTeamController: OurTeamController
    private lateinit var sectionsHeaderController: SectionsHeaderController

    private val onScrollListener = simpleOnScrollListener {
        mainActivityConnector?.onScroll()
    }

    override fun provideLayout(): Int = R.layout.fragment_landing

    override fun providePresenter(): LandingPageContract.Presenter = landingPagePresenter

    override fun injectDependencies() = LandingFragmentComponent.init(findDependencies()).inject(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivityConnector = activity as IMainActivityConnector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = landingAdapter
            addOnScrollListener(onScrollListener)
        }

        parallaxScrollListener = ParallaxScrollListener().apply {
            initWith(recyclerView, lifecycle)
        }

        headerController = HeaderController()
        noDataController = NoDataController {
            presenter.retryLoading()
        }

        aboutController = AboutController()
        sectionsHeaderController = SectionsHeaderController()
        quotesController = QuotesController(parallaxScrollListener)
        ourTeamController = OurTeamController(parallaxScrollListener) {
            browseUrl(it)
        }

        presenter.loadLandingData()
    }

    override fun render(screenModel: LandingScreenModel) {
        val itemList = ItemList.create()

        when {
            screenModel.loadState == LoadState.PROGRESS -> showProgress()
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

    override fun showProgress() = with(hatsProgressAnimationView) {
        startAnimation()
        show()
    }

    override fun hideProgress() = with(hatsProgressAnimationView) {
        stopAnimation()
        gone()
    }

    override fun onDetach() {
        super.onDetach()
        mainActivityConnector = null
    }

    override fun onDestroy() {
        super.onDestroy()
        parallaxScrollListener = null
    }
}