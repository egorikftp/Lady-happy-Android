package com.egoriku.photoreport.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.core.di.findDependencies
import com.egoriku.ladyhappy.arch.fragment.BaseInjectableFragment
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.injectViewModel
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.photoreport.databinding.FragmentPhotoReportBinding
import com.egoriku.photoreport.di.PhotoReportFragmentComponent
import com.egoriku.photoreport.presentation.controller.PhotoReportCarouselController
import com.egoriku.photoreport.presentation.controller.PhotoReportHeaderController
import com.egoriku.ui.controller.NoDataController
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

class PhotoReportFragment : BaseInjectableFragment(0) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PhotoReportViewModel
    private lateinit var binding: FragmentPhotoReportBinding

    private lateinit var noDataController: NoDataController
    private lateinit var photoReportHeaderController: PhotoReportHeaderController
    private lateinit var photoReportCarouselController: PhotoReportCarouselController

    private val photoReportAdapter = EasyAdapter()

    override fun injectDependencies() = PhotoReportFragmentComponent.init(findDependencies()).inject(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentPhotoReportBinding.inflate(inflater, container, false).apply {
            binding = this
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = injectViewModel(viewModelFactory)

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
