package com.egoriku.ladyhappy.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.egoriku.ladyhappy.extensions.getString
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.ui.R
import com.egoriku.ladyhappy.ui.databinding.ViewPhotoGalleryOverlayBinding

class PhotoOverlayActions @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = ViewPhotoGalleryOverlayBinding.inflate(
            inflater(), this, true
    )

    private val titleFormat = getString(R.string.proto_overlay_title_format)

    var onCloseClick: (() -> Unit)? = null
        set(value) {
            field = value

            viewBinding.close.setOnClickListener {
                onCloseClick?.invoke()
            }
        }

    fun setTitle(position: Int, count: Int) {
        viewBinding.title.text = titleFormat.format(position, count)
    }
}