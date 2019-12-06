package com.egoriku.mozaik.binder

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.extensions.inflate
import com.egoriku.mozaik.MozaikLayout
import com.egoriku.mozaik.R
import com.egoriku.mozaik.model.MozaikImageItem
import com.egoriku.mozaik.model.Photo

class PhotosBinder(
        private val callback: (photos: List<Photo>, index: Int) -> Unit
) {

    private class Holder(itemView: View) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
    }

    fun displayPhotos(photos: List<MozaikImageItem>, container: MozaikLayout) {
        container.visibility = if (photos.isEmpty()) View.GONE else View.VISIBLE

        if (photos.isEmpty()) {
            return
        }

        val i = photos.size - container.childCount

        for (j in 0 until i) {
            val root = container.inflate(R.layout.item_image)
            val holder = Holder(root)
            root.tag = holder
            container.addView(root)
        }

        container.setPhotos(photos)

        for (childPosition in 0 until container.childCount) {
            val view = container.getChildAt(childPosition)
            val holder = view.tag as Holder

            if (childPosition < photos.size) {
                val image = photos[childPosition]

                holder.imageView.setOnClickListener {
                    openImages(photos, childPosition)
                }

                val url = image.getPreviewUrl()

                if (url.isNotEmpty()) {
                    Glide.with(container.context)
                            .load(url)
                            .placeholder(R.drawable.bg_gray)
                            .into(holder.imageView)
                    view.visibility = View.VISIBLE
                } else {
                    view.visibility = View.GONE
                }
            } else {
                view.visibility = View.GONE
            }
        }
    }

    private fun openImages(photos: List<MozaikImageItem>, index: Int) {
        callback(photos.map { it.photo }, index)
    }
}