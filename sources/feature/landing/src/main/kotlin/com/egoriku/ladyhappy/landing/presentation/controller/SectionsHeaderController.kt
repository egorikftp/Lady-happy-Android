package com.egoriku.ladyhappy.landing.presentation.controller

import android.view.ViewGroup
import androidx.annotation.StringRes
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.landing.databinding.AdapterItemSectionHeaderBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class SectionsHeaderController : BindableItemController<Int, SectionsHeaderController.Holder>() {

    override fun getItemId(data: Int) = data.hashCode().toString()

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemSectionHeaderBinding.inflate(parent.inflater(), parent, false))

    inner class Holder(
            private val itemBinding: AdapterItemSectionHeaderBinding
    ) : BindableViewHolder<Int>(itemBinding.root) {

        override fun bind(@StringRes stringRes: Int) {
            itemBinding.sectionHeader.text = itemView.context.getString(stringRes)
        }
    }
}