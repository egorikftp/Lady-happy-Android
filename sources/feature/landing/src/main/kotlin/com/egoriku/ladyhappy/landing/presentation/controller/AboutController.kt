package com.egoriku.ladyhappy.landing.presentation.controller

import android.view.ViewGroup
import android.widget.TextView
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.landing.databinding.AdapterItemAboutBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class AboutController : BindableItemController<String, AboutController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
        Holder(AdapterItemAboutBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: String) = data.hashCode().toString()

    inner class Holder(
        private val itemBinding: AdapterItemAboutBinding
    ) : BindableViewHolder<String>(itemBinding.root) {

        override fun bind(data: String) {
            (itemBinding.aboutContent as TextView).text = data
        }
    }
}
