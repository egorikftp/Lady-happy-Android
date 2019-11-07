package com.egoriku.ladyhappy.catalog.presentation.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.catalog.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_catalog.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val catalogAdapter = EasyAdapter().apply {
        setFirstInvisibleItemEnabled(false)
    }

    private lateinit var catalogController: CatalogController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        catalogController = CatalogController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        catalogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catalogAdapter
            addItemDecoration(MarginItemDecoration(context.resources.getDimension(R.dimen.adapter_item_catalog_margin).toInt()))
        }

        catalogAdapter.setItems(
                ItemList.create()
                        .addAll(listOf("1", "2", "3", "4", "5", "6"), catalogController)
        )
    }

    internal class CatalogController : BindableItemController<String, CatalogController.Holder>() {

        override fun createViewHolder(parent: ViewGroup) = Holder(parent)

        override fun getItemId(data: String) = data.hashCode().toString()

        inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_catalog), LayoutContainer {

            override val containerView: View = itemView

            override fun bind(data: String) {
                textView.text = "Широкополая шляпка"

                listOf(
                        imageView,
                        imageView2,
                        imageView3,
                        imageView4,
                        imageView5,
                        imageView6,
                        imageView7
                ).forEach {
                    Glide.with(itemView.context)
                            .load("https://lady-happy.com/assets/images/portfolio/5.jpg")
                            .into(it)
                }
            }
        }
    }

    class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = spaceHeight
                }
                left =  spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
        }
    }
}
