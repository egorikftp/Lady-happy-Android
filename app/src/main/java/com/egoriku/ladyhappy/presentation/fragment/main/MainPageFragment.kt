package com.egoriku.ladyhappy.presentation.fragment.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.egoriku.corelib_kt.dsl.hide
import com.egoriku.corelib_kt.dsl.show
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.cast
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.base.BaseInjectableFragment
import com.egoriku.ladyhappy.presentation.fragment.main.conroller.HeaderController
import kotlinx.android.synthetic.main.fragment_main_page.*
import ru.surfstudio.easyadapter.recycler.EasyAdapter
import ru.surfstudio.easyadapter.recycler.ItemList
import javax.inject.Inject

class MainPageFragment : BaseInjectableFragment<MainPageContract.View, MainPageContract.Presenter>(), MainPageContract.View {

    @Inject
    lateinit var mainPagePresenter: MainPageContract.Presenter

    private val mainPageAdapter = EasyAdapter()

    private lateinit var headerController: HeaderController

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

        headerController = HeaderController()

        showInformation()
    }

    override fun showLoading() {
        mainProgressBar.show()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
    }

    override fun showInformation() {
        mainPageAdapter.setItems(
                ItemList.create()
                        .addIf(true, headerController)
        )
    }
}