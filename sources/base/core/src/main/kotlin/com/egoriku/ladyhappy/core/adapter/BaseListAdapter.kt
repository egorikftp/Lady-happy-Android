package com.egoriku.ladyhappy.core.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T, VH : BaseViewHolder<T>>(
        diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) = onBindViewHolder(holder, position, getItem(position))

    abstract fun onBindViewHolder(holder: VH, position: Int, model: T)
}