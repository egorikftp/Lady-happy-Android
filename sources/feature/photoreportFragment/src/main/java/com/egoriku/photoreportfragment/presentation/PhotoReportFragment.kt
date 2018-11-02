package com.egoriku.photoreportfragment.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.core.actions.common.IMainActivityConnector
import com.egoriku.core.di.findDependencies
import com.egoriku.photoreportfragment.R
import com.egoriku.photoreportfragment.di.PhotoReportFragmentComponent
import com.egoriku.photoreportfragment.presentation.controller.ErrorStateController
import com.egoriku.photoreportfragment.presentation.controller.PhotoReportCarouselController
import com.egoriku.photoreportfragment.presentation.controller.PhotoReportHeaderController
import com.egoriku.ui.arch.fragment.BaseInjectableFragment
import com.egoriku.ui.ktx.gone
import com.egoriku.ui.ktx.show
import kotlinx.android.synthetic.main.fragment_photo_report.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

class PhotoReportFragment : BaseInjectableFragment<PhotoReportContract.View, PhotoReportContract.Presenter>(), PhotoReportContract.View {

    companion object {
        fun newInstance() = PhotoReportFragment()
    }

    @Inject
    lateinit var photoReportPresenter: PhotoReportContract.Presenter

    private var mainActivityConnector: IMainActivityConnector? = null

    private lateinit var errorStateController: ErrorStateController
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

    override fun onDetach() {
        super.onDetach()
        mainActivityConnector = null
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
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    mainActivityConnector?.onScroll()
                }
            })
        }

        errorStateController = ErrorStateController(onReloadClickListener = {
            presenter.loadData()
        })

        photoReportHeaderController = PhotoReportHeaderController()
        photoReportCarouselController = PhotoReportCarouselController(viewPool)
    }

    override fun render(screenModel: ScreenModel) {
        val itemList = ItemList.create()
                .add(photoReportHeaderController)
                .addAll(screenModel.photoReports, photoReportCarouselController)
                .addIf(screenModel.isPhotoReportsEmpty(), errorStateController)

        photoReportAdapter.setItems(itemList)
    }

    override fun showLoading() = progressView.show()

    override fun hideLoading() = progressView.gone()
}
