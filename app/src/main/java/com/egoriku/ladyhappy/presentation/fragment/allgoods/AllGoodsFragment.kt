package com.egoriku.ladyhappy.presentation.fragment.allgoods

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
import com.egoriku.ladyhappy.di.allgoods.AllGoodsModule
import com.egoriku.ladyhappy.di.allgoods.DaggerAllGoodsComponent
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.adapter.animator.DefaultItemAnimator
import com.egoriku.ladyhappy.presentation.fragment.allgoods.recycler.controller.CategoriesController
import com.egoriku.ladyhappy.presentation.fragment.allgoods.recycler.controller.ErrorStateController
import kotlinx.android.synthetic.main.fragment_all_goods.*
import org.jetbrains.anko.support.v4.toast
import ru.surfstudio.easyadapter.recycler.EasyAdapter
import ru.surfstudio.easyadapter.recycler.ItemList
import javax.inject.Inject

class AllGoodsFragment : BaseFragment<AllGoodsContract.View, AllGoodsContract.Presenter>(), AllGoodsContract.View {

    @Inject
    lateinit var allGoodsPresenter: AllGoodsPresenter

    private lateinit var categoriesController: CategoriesController
    private lateinit var errorStateController: ErrorStateController

    private val allGoodsAdapter = EasyAdapter()

    companion object {
        fun newInstance() = AllGoodsFragment()
    }

    override fun initPresenter() = allGoodsPresenter

    override fun injectDependencies() {
        DaggerAllGoodsComponent.builder()
                .appComponent(App.instance.appComponent)
                .allGoodsModule(AllGoodsModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_all_goods, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTitle(R.string.navigation_drawer_all_goods)

        initRecyclerView()

        presenter.loadData()
    }

    private fun initRecyclerView() {
        recyclerViewAllGoods.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = allGoodsAdapter
        }

        categoriesController = CategoriesController(onClickListener = {
            toast("on ${it.title} click")
        })

        errorStateController = ErrorStateController(onReloadClickListener = {
            presenter.loadData()
        })
    }

    override fun render(screenModel: AllGoodsScreenModel) {
        val itemList = ItemList.create()
                .addAll(screenModel.categories, categoriesController)
                .addIf(screenModel.isEmpty(), errorStateController)

        allGoodsAdapter.setItems(itemList)
    }

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun showTitle(@StringRes title: Int) {
        (activity as MainActivity).setUpToolbar(title)
    }

    override fun showLoading() {
        progressView.show()
    }

    override fun hideLoading() {
        progressView.hide()
    }
}
