package com.egoriku.giugi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.egoriku.giugi.R
import com.egoriku.giugi.data.Toy
import ext.adapter.BindItem
import ext.adapter.Binder
import kotlinx.android.synthetic.main.layout_images.view.*

@BindItem(layout = R.layout.layout_images, holder = ToysItem.ToysViewHolder::class)
class ToysItem(private val toy: Toy) {

    @Binder
    fun bind(holder: ToysViewHolder) {

        holder.textViewName.text = toy.name

        /*Glide.with(context)
                .load(toy.url)
                .asBitmap()
                .fitCenter()
                .into(holder.imageView)*/
    }

    class ToysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.image_name
        var imageView: ImageView = itemView.image_item
    }
}
