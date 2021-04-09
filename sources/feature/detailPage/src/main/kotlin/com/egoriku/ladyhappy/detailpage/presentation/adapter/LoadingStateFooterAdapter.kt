package com.egoriku.ladyhappy.detailpage.presentation.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.egoriku.ladyhappy.detailpage.presentation.adapter.viewholder.LoadStateFooterViewHolder

class LoadingStateFooterAdapter(
        private val retry: () -> Unit
) : LoadStateAdapter<LoadStateFooterViewHolder>() {

    override fun onBindViewHolder(
            holder: LoadStateFooterViewHolder,
            loadState: LoadState
    ) = holder.bind(loadState)

    override fun onCreateViewHolder(
            parent: ViewGroup,
            loadState: LoadState
    ) = LoadStateFooterViewHolder.create(parent, retry)
}
