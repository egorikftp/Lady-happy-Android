package com.egoriku.ladyhappy.detailpage.presentation.adapter.viewholder

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.detailpage.databinding.AdapterItemLoadStateFooterBinding
import com.egoriku.ladyhappy.extensions.inflater

class LoadStateFooterViewHolder(
        private val binding: AdapterItemLoadStateFooterBinding,
        retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMessage.text = loadState.error.localizedMessage
        }
        binding.progresBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMessage.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit) = LoadStateFooterViewHolder(
                binding = AdapterItemLoadStateFooterBinding.inflate(parent.inflater(), parent, false),
                retry = retry
        )
    }
}