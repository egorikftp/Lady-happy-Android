package com.egoriku.photoreport.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.feature.PhotoReportsFeature
import com.egoriku.extensions.gone
import com.egoriku.extensions.visible
import com.egoriku.photoreport.R
import com.egoriku.photoreport.databinding.FragmentPhotoReportBinding
import com.egoriku.photoreport.presentation.controller.PhotoReportCarouselController
import com.egoriku.photoreport.presentation.controller.PhotoReportHeaderController
import com.egoriku.ui.controller.NoDataController
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class PhotoReportFragment : Fragment(R.layout.fragment_photo_report), PhotoReportsFeature {

    private val binding: FragmentPhotoReportBinding by viewBinding()

    private val viewModel: PhotoReportViewModel by lifecycleScope.viewModel(this)

    private val photoReportAdapter = EasyAdapter()

    private lateinit var noDataController: NoDataController
    private lateinit var photoReportHeaderController: PhotoReportHeaderController
    private lateinit var photoReportCarouselController: PhotoReportCarouselController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.screenState.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    private fun initRecyclerView() {
        val viewPool = RecyclerView.RecycledViewPool()

        binding.recyclerViewAllGoods.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoReportAdapter
        }

        noDataController = NoDataController {
            viewModel.retryLoading()
        }

        photoReportHeaderController = PhotoReportHeaderController()
        photoReportCarouselController = PhotoReportCarouselController(viewPool)
    }

    private fun render(screenModel: ScreenModel) {
        val itemList = ItemList.create()

        when (screenModel.loadState) {
            LoadState.PROGRESS -> showLoading()
            else -> hideLoading()
        }

        itemList.addIf(screenModel.isEmpty() && screenModel.loadState == LoadState.ERROR_LOADING, noDataController)

        screenModel.photoReports?.let {
            itemList.add(photoReportHeaderController)
                    .addAll(it, photoReportCarouselController)
        }

        photoReportAdapter.setItems(itemList)
    }

    private fun showLoading() = binding.progressView.visible()

    private fun hideLoading() = binding.progressView.gone()
}
