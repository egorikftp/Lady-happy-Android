package com.egoriku.ladyhappy.presentation.fragment.allgoods

import android.app.Activity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.egoriku.corelib_kt.dsl.hide
import com.egoriku.corelib_kt.dsl.show
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.cast
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.activity.newpost.DetailCategoryActivity
import com.egoriku.ladyhappy.presentation.adapter.animator.DefaultItemAnimator
import com.egoriku.ladyhappy.presentation.base.BaseInjectableFragment
import com.egoriku.ladyhappy.presentation.fragment.allgoods.controller.CategoriesController
import com.egoriku.ladyhappy.presentation.fragment.allgoods.controller.ErrorStateController
import com.egoriku.ladyhappy.presentation.fragment.allgoods.controller.NewsController
import com.egoriku.ladyhappy.presentation.fragment.allgoods.controller.NewsHeaderController
import kotlinx.android.synthetic.main.fragment_all_goods.*
import org.jetbrains.anko.support.v4.intentFor
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList


class AllGoodsFragment : BaseInjectableFragment<AllGoodsContract.View, AllGoodsContract.Presenter>(), AllGoodsContract.View {

   // @Inject
    lateinit var allGoodsPresenter: AllGoodsContract.Presenter

    private lateinit var categoriesController: CategoriesController
    private lateinit var errorStateController: ErrorStateController
    private lateinit var newsHeaderController: NewsHeaderController
    private lateinit var newsController: NewsController

    private val allGoodsAdapter = EasyAdapter()

    companion object {
        fun newInstance() = AllGoodsFragment()

        const val EXTRA_ANIMAL_ITEM = "EXTRA_ANIMAL_ITEM"
        const val EXTRA_ANIMAL_IMAGE_TRANSITION_NAME = "EXTRA_ANIMAL_IMAGE_TRANSITION_NAME"
    }

    override fun initPresenter() = allGoodsPresenter

    override fun provideLayout(): Int = R.layout.fragment_all_goods

    override fun injectDependencies() {
      /*  DaggerAllGoodsComponent.builder()
                .appComponent((activity?.applicationContext as Application).getAppComponent())
                .allGoodsModule(AllGoodsModule())
                .build()
                .inject(this)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTitle(R.string.navigation_drawer_all_goods)

        initRecyclerView()

        presenter.loadData()
    }

    private fun initRecyclerView() {
        recyclerViewAllGoods.apply {
            layoutManager = LinearLayoutManager(context).apply {
                initialPrefetchItemCount = 5
            }
            itemAnimator = DefaultItemAnimator()
            adapter = allGoodsAdapter
            setHasFixedSize(true)
        }

        categoriesController = CategoriesController(onClickListener = { model, imageView ->
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity as Activity,
                    imageView,
                    ViewCompat.getTransitionName(imageView))

            startActivity(
                    intentFor<DetailCategoryActivity>().apply {
                        putExtra(EXTRA_ANIMAL_ITEM, model)
                        putExtra(EXTRA_ANIMAL_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(imageView))
                    }, options.toBundle())
        })

        errorStateController = ErrorStateController(onReloadClickListener = {
            presenter.loadData()
        })

        newsHeaderController = NewsHeaderController()
        newsController = NewsController()
    }

    override fun render(screenModel: AllGoodsScreenModel) {
        val itemList = ItemList.create()
                .addAll(screenModel.categories, categoriesController)
                .addIf(!screenModel.newsEmpty(), newsHeaderController)
                .addAll(screenModel.news, newsController)
                .addIf(screenModel.isEmpty(), errorStateController)

        allGoodsAdapter.setItems(itemList)
    }

    override fun showTitle(@StringRes title: Int) {
        activity?.cast<MainActivity>()?.setUpToolbar(title)
    }

    override fun showLoading() {
        progressView.show()
    }

    override fun hideLoading() {
        progressView.hide()
    }
}
