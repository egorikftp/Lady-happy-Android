package com.egoriku.mozaik.binder

import android.view.View
import android.widget.ImageView
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.inflate
import com.egoriku.mozaik.MozaikLayout
import com.egoriku.mozaik.R
import com.egoriku.mozaik.model.MozaikImageItem

class NonClickablePhotosBinder(
        val onImageLoad: ((view: ImageView, image: MozaikImageItem) -> Unit)
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
                val image: MozaikImageItem = photos[childPosition]

                onImageLoad(holder.imageView, image)
            } else {
                view.gone()
            }
        }
    }
}