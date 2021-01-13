package com.egoriku.ladyhappy.photoreport.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.core.feature.PhotoReportsFeature
import com.egoriku.ladyhappy.extensions.drawableCompat
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.ladyhappy.photoreport.R
import com.egoriku.ladyhappy.photoreport.databinding.FragmentPhotoReportBinding
import com.egoriku.ladyhappy.photoreport.presentation.adapter.PhotoReportAdapter
import com.egoriku.ladyhappy.photoreport.presentation.state.PhotoReportUiState
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class PhotoReportFragment : ScopeFragment(R.layout.fragment_photo_report), PhotoReportsFeature {

    private val binding by viewBinding(FragmentPhotoReportBinding::bind)

    private val viewModel by viewModel<PhotoReportViewModel>()

    private var photoReportAdapter: PhotoReportAdapter by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.initRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is PhotoReportUiState.Error -> processError()
                    is PhotoReportUiState.Loading -> processLoading()
                    is PhotoReportUiState.Success -> processSuccess(uiState)
                }
            }
        }
    }

    private fun FragmentPhotoReportBinding.initRecyclerView() {
        photoReportAdapter = PhotoReportAdapter()

        recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoReportAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
                setDrawable(drawableCompat(R.drawable.bg_photoreport_divider))
            })
            /*addItemDecoration(
                    ColorDividerItemDecoration(
                            height = getDimen(R.dimen.adapter_item_image_decorator_size),
                            backgroundColor = colorFromAttr(R.attr.colorPlaceholder)
                    )
            )*/
        }

        noDataLayout.retryButton.setOnClickListener {
            viewModel.retryLoading()
        }
    }

    private fun processSuccess(photoReportUiState: PhotoReportUiState.Success) {
        hideLoading()
        hideError()

        photoReportAdapter.submitList(photoReportUiState.photoReports)
    }

    private fun processLoading() {
        hideError()
        showLoading()
    }

    private fun processError() {
        hideLoading()
        showError()
    }

    private fun showLoading() = binding.progressView.visible()

    private fun hideLoading() = binding.progressView.gone()

    private fun showError() = binding.noDataLayout.root.visible()

    private fun hideError() = binding.noDataLayout.root.gone()
}
