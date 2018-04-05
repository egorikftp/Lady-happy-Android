package com.egoriku.ladyhappy.presentation.adapter.pagination

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.presentation.fragment.allgoods.recycler.controller.stub.Stub
import com.egoriku.ladyhappy.presentation.adapter.dsl.toStub
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class CategoriesStubController : BindableItemController<Stub, CategoriesStubController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(stub: Stub) = stub.id

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Stub>(parent, R.layout.adapter_item_category) {

        init {
            itemView.toStub()
        }

        override fun bind(stub: Stub) {}
    }
}