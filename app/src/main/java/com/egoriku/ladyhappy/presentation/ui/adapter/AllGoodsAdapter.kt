package com.egoriku.ladyhappy.presentation.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import com.egoriku.ladyhappy.presentation.ui.adapter.model.BaseDisplayableItem
import com.egoriku.ladyhappy.presentation.ui.adapter.model.CategoriesAdapterDelegate
import com.egoriku.ladyhappy.presentation.ui.adapter.model.SectionType
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class AllGoodsAdapter(val activity: Activity?) : ListDelegationAdapter<List<BaseDisplayableItem>>() {

    private var list: MutableList<BaseDisplayableItem> = mutableListOf()

    init {
        delegatesManager.apply {
            addDelegate(CategoriesAdapterDelegate(activity))
        }
    }

    @SuppressLint("SwitchIntDef")
    fun addItems(@SectionType type: Int, newDataList: List<BaseDisplayableItem>) {
        when (type) {
            SectionType.CATEGORIES -> {
                list.addAll(0, newDataList)
                setItems(newDataList)
                notifyItemRangeInserted(0, list.size)
            }

            SectionType.NEWS -> {
                val positionStart = list.size + 1

                list.addAll(newDataList)
                setItems(newDataList)
                notifyItemRangeInserted(positionStart, newDataList.size)
            }
        }
    }
}