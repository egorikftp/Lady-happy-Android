package com.egoriku.ladyhappy.presentation.fragment.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.egoriku.corelib_kt.dsl.hide
import com.egoriku.corelib_kt.dsl.show
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.cast
import com.egoriku.ladyhappy.common.parallax.ParallaxScrollListener
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.base.BaseInjectableFragment
import com.egoriku.ladyhappy.presentation.fragment.main.conroller.AboutController
import com.egoriku.ladyhappy.presentation.fragment.main.conroller.HeaderController
import com.egoriku.ladyhappy.presentation.fragment.main.conroller.QuotesController
import kotlinx.android.synthetic.main.fragment_main_page.*
import ru.surfstudio.easyadapter.recycler.EasyAdapter
import ru.surfstudio.easyadapter.recycler.ItemList
import javax.inject.Inject

class MainPageFragment : BaseInjectableFragment<MainPageContract.View, MainPageContract.Presenter>(), MainPageContract.View {

    @Inject
    lateinit var mainPagePresenter: MainPageContract.Presenter

    private val mainPageAdapter = EasyAdapter()
    private lateinit var parallaxScrollListener: ParallaxScrollListener

    private lateinit var headerController: HeaderController
    private lateinit var aboutController: AboutController
    private lateinit var quotasController: QuotesController

    companion object {
        fun newInstance() = MainPageFragment()
    }

    override fun provideLayout(): Int = R.layout.fragment_main_page

    override fun initPresenter(): MainPageContract.Presenter = mainPagePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.cast<MainActivity>()?.setUpToolbar(R.string.navigation_main)
        initViews()
    }

    override fun initViews() {

        mainPageRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainPageAdapter
        }

        parallaxScrollListener = ParallaxScrollListener().apply {
            initWith(mainPageRecyclerView)
        }

        headerController = HeaderController()
        aboutController = AboutController()
        quotasController = QuotesController(parallaxScrollListener)

        showInformation()
    }

    override fun showLoading() {
        mainProgressBar.show()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
    }

    override fun showInformation() {
        val s = "Женщина должна притягивать своей красотой взгляды окружающих. Красивый аксессуар всегда был неотъемлемой частью гардероба. Он подчеркивал женскую загадочность и робкость... \n" +
                "\n" +
                "В последнее время изделия из натуральных материалов стали популярны на рынке. Все обусловленно тем, что они делаются вручную и, в конечном итоге, являются уникальным предметом. Компания \"Дамское счастье\" сделает все, чтобы вы почувствовали себя той самой неповторимой."

        val quotes = "Главное в платье - это женщина, которая его надевает."
        mainPageAdapter.setItems(
                ItemList.create()
                        .addIf(true, headerController)
                        .add(s, aboutController)
                        .add(quotes, quotasController)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        parallaxScrollListener.detach()
    }
}