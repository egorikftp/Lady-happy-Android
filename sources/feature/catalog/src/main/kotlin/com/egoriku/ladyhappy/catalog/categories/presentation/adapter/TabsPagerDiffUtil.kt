package com.egoriku.ladyhappy.catalog.categories.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem

class TabsPagerDiffUtil(
        private val oldList: List<TabItem>,
        private val newList: List<TabItem>
) : DiffUtil.Callback() {

    enum class PayloadKey {
        VALUE
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].categoryId == newList[newItemPosition].categoryId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].editCount == newList[newItemPosition].editCount
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        return listOf(PayloadKey.VALUE)
    }
}