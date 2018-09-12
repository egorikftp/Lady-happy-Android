package com.egoriku.ladyhappy.presentation.fragment.allgoods

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.egoriku.featureactivitymain.presentation.activity.MainActivity
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.cast
import com.egoriku.ladyhappy.presentation.adapter.animator.DefaultItemAnimator
import com.egoriku.ladyhappy.presentation.fragment.allgoods.controller.NewsHeaderController
import com.egoriku.ladyhappy.presentation.fragment.allgoods.controller.PhotoReportController
import com.egoriku.photoreportfragment.presentation.AllGoodsContract
import com.egoriku.photoreportfragment.presentation.ScreenModel
import com.egoriku.photoreportfragment.presentation.controller.ErrorStateController
import com.egoriku.ui.arch.fragment.BaseInjectableFragment
import com.egoriku.ui.ktx.hide
import com.egoriku.ui.ktx.show
import kotlinx.android.synthetic.main.fragment_all_goods.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class AllGoodsFragment : BaseInjectableFragment<AllGoodsContract.View, AllGoodsContract.Presenter>(), AllGoodsContract.View {

    // @Inject
    lateinit var allGoodsPresenter: AllGoodsContract.Presenter

    private lateinit var errorStateController: ErrorStateController
    private lateinit var newsHeaderController: NewsHeaderController
    private lateinit var photoReportController: PhotoReportController

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

        errorStateController = ErrorStateController(onReloadClickListener = {
            presenter.loadData()
        })

        newsHeaderController = NewsHeaderController()
        photoReportController = PhotoReportController()
    }

    override fun render(screenModel: ScreenModel) {
        val itemList = ItemList.create()
                .addAll(screenModel.photoReports, photoReportController)
                .addIf(screenModel.isPhotoReportsEmpty(), errorStateController)

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
