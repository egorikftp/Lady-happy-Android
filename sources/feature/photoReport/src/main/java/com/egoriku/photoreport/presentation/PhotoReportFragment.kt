package com.egoriku.photoreport.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.core.common.IMainActivityConnector
import com.egoriku.core.di.findDependencies
import com.egoriku.ladyhappy.arch.fragment.BaseInjectableFragment
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.show
import com.egoriku.photoreport.R
import com.egoriku.photoreport.di.PhotoReportFragmentComponent
import com.egoriku.photoreport.presentation.controller.PhotoReportCarouselController
import com.egoriku.photoreport.presentation.controller.PhotoReportHeaderController
import com.egoriku.ui.controller.NoDataController
import com.egoriku.ui.dsl.simpleOnScrollListener
import kotlinx.android.synthetic.main.fragment_photo_report.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

class PhotoReportFragment : BaseInjectableFragment<PhotoReportContract.View, PhotoReportContract.Presenter>(), PhotoReportContract.View {

    @Inject
    lateinit var photoReportPresenter: PhotoReportContract.Presenter

    private var mainActivityConnector: IMainActivityConnector? = null

    private lateinit var noDataController: NoDataController
    private lateinit var photoReportHeaderController: PhotoReportHeaderController
    private lateinit var photoReportCarouselController: PhotoReportCarouselController

    private val photoReportAdapter = EasyAdapter()

    override fun providePresenter() = photoReportPresenter

    override fun provideLayout(): Int = R.layout.fragment_photo_report

    override fun injectDependencies() = PhotoReportFragmentComponent.init(findDependencies()).inject(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivityConnector = activity as IMainActivityConnector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        presenter.loadData()
    }

    private fun initRecyclerView() {
        val viewPool = RecyclerView.RecycledViewPool()

        recyclerViewAllGoods.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoReportAdapter

            simpleOnScrollListener {
                mainActivityConnector?.onScroll()
            }
        }

        noDataController = NoDataController {
            presenter.loadData()
        }

        photoReportHeaderController = PhotoReportHeaderController()
        photoReportCarouselController = PhotoReportCarouselController(viewPool)
    }

    override fun render(screenModel: ScreenModel) {
        val itemList = ItemList.create()

        when {
            screenModel.loadState == LoadState.PROGRESS -> showLoading()
            else -> hideLoading()
        }

        itemList.addIf(screenModel.isEmpty() || screenModel.loadState == LoadState.ERROR_LOADING, noDataController)

        screenModel.photoReports?.let {
            itemList.add(photoReportHeaderController)
                    .addAll(it, photoReportCarouselController)
        }

        photoReportAdapter.setItems(itemList)
    }

    override fun showLoading() = progressView.show()

    override fun hideLoading() = progressView.gone()

    override fun onDetach() {
        super.onDetach()
        mainActivityConnector = null
    }
}
