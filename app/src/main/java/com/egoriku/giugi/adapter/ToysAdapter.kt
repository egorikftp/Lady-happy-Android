package com.egoriku.giugi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.egoriku.giugi.R
import com.egoriku.giugi.data.Toy
import kotlinx.android.synthetic.main.layout_images.view.*


class ToysAdapter(private val context: Context, private val toys: List<Toy>) : RecyclerView.Adapter<ToysAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_images, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, url) = toys[position]

        holder.textViewName.text = name

        Glide.with(context)
                .load(url)
                .asBitmap()
                .fitCenter()
                .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return toys.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.image_name
        var imageView: ImageView = itemView.image_item
    }
}
