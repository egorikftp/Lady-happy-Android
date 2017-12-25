package com.egoriku.ladyhappy.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.arch.BaseFragment
import com.egoriku.corelib_kt.dsl.hide
import com.egoriku.corelib_kt.dsl.inflate
import com.egoriku.corelib_kt.dsl.show
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.di.allgoods.AllGoodsComponent
import com.egoriku.ladyhappy.di.allgoods.AllGoodsModule
import com.egoriku.ladyhappy.di.allgoods.DaggerAllGoodsComponent
import com.egoriku.ladyhappy.domain.models.CategoryModel
import com.egoriku.ladyhappy.presentation.presenters.AllGoodsContract
import com.egoriku.ladyhappy.presentation.presenters.impl.AllGoodsPresenter
import com.egoriku.ladyhappy.presentation.ui.activity.MainActivity
import com.egoriku.ladyhappy.presentation.ui.adapter.AllGoodsAdapter
import com.egoriku.ladyhappy.presentation.ui.adapter.model.SectionType
import kotlinx.android.synthetic.main.fragment_all_goods.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AllGoodsFragment : BaseFragment<AllGoodsContract.View, AllGoodsContract.Presenter>(), AllGoodsContract.View {

    override fun showNews() {

    }

    @Inject
    lateinit var allGoodsPresenter: AllGoodsPresenter

    private lateinit var component: AllGoodsComponent

    private lateinit var allGoodsAdapter: AllGoodsAdapter

    companion object {
        fun newInstance() = AllGoodsFragment()
    }

    override fun initPresenter() = allGoodsPresenter

    override fun injectDependencies() {
        component = DaggerAllGoodsComponent.builder()
                .appComponent(App.instance.appComponent)
                .allGoodsModule(AllGoodsModule(this))
                .build()
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_all_goods, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTitle(R.string.navigation_drawer_all_goods)

        presenter.getCategories()

        initRecyclerView()
    }

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun showTitle(@StringRes title: Int) {
        (activity as MainActivity).setUpToolbar(title)
    }

    override fun showCategories(categories: List<CategoryModel>) {
        allGoodsAdapter.addItems(SectionType.CATEGORIES, categories)
    }

    override fun showLoading() {
        progressView.show()
    }

    override fun hideLoading() {
        progressView.hide()
    }

    override fun showMessage(message: String) {

    }

    override fun showNoNetwork() {

    }

    private fun initRecyclerView() {
        allGoodsAdapter = AllGoodsAdapter(activity)

        recycler_all_goods.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allGoodsAdapter
        }
    }
}
