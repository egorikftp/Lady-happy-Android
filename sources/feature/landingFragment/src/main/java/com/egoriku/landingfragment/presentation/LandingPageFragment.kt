package com.egoriku.landingfragment.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.core.actions.common.IMainActivityConnector
import com.egoriku.core.di.findDependencies
import com.egoriku.core.model.ILandingModel
import com.egoriku.landingfragment.R
import com.egoriku.landingfragment.common.parallax.ParallaxScrollListener
import com.egoriku.landingfragment.di.LandingFragmentComponent
import com.egoriku.landingfragment.presentation.controller.*
import com.egoriku.ui.arch.fragment.BaseInjectableFragment
import com.egoriku.ui.ktx.browseUrl
import com.egoriku.ui.ktx.gone
import com.egoriku.ui.ktx.show
import kotlinx.android.synthetic.main.fragment_main_page.*
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
    private val mainPageAdapter = EasyAdapter()

    private lateinit var parallaxScrollListener: ParallaxScrollListener
    private lateinit var headerController: HeaderController
    private lateinit var aboutController: AboutController
    private lateinit var quotesController: QuotesController
    private lateinit var sectionsHeaderController: SectionsHeaderController
    private lateinit var ourTeamController: OurTeamController

    override fun provideLayout(): Int = R.layout.fragment_main_page

    override fun providePresenter(): LandingPageContract.Presenter = landingPagePresenter

    override fun injectDependencies() = LandingFragmentComponent.init(findDependencies()).inject(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivityConnector = activity as IMainActivityConnector
    }

    override fun onDetach() {
        super.onDetach()
        mainActivityConnector = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainPageAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    mainActivityConnector?.onScroll()
                }
            })
        }

        parallaxScrollListener = ParallaxScrollListener().apply {
            initWith(recyclerView, lifecycle)
        }

        headerController = HeaderController()
        aboutController = AboutController()
        sectionsHeaderController = SectionsHeaderController()
        quotesController = QuotesController(parallaxScrollListener)
        ourTeamController = OurTeamController {
            browseUrl(it)
        }

        presenter.loadLandingData()
    }

    override fun showLoading() = progressBar.show()

    override fun hideLoading() = progressBar.gone()

    override fun render(model: ILandingModel) {
        mainPageAdapter.setItems(
                ItemList.create()
                        .add(headerController)
                        .add(model.aboutInfo, aboutController)
                        .addIf(model.quotes.isNotEmpty(), R.string.adapter_item_header_quotes, sectionsHeaderController)
                        .addIf(model.quotes.isNotEmpty(), model.quotes, quotesController)
                        .addIf(model.teamMembers.isNotEmpty(), R.string.adapter_item_header_our_team, sectionsHeaderController)
                        .addAll(model.teamMembers, ourTeamController)
        )
    }
}