package com.egoriku.ladyhappy.common

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView

interface DiffCallBack {

    fun <T> RecyclerView.Adapter<*>.autoNotify(old: List<T>, new: List<T>, compare: (T, T) -> Boolean) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = compare(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition] == new[newItemPosition]
        })

        diff.dispatchUpdatesTo(this)
    }
}