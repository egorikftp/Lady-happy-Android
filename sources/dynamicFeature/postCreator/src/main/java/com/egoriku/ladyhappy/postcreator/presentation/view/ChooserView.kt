package com.egoriku.ladyhappy.postcreator.presentation.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.egoriku.ladyhappy.extensions.addRipple
import com.egoriku.ladyhappy.extensions.getDimen
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.extensions.withStyledAttributes
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.ViewChooserBinding
import com.egoriku.ui.R as R_ui

class ChooserView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewChooserBinding.inflate(inflater(), this)

    private var hintText: String? = null

    private var defaultIconDrawable: Drawable? = null
    private var clearIconDrawable: Drawable? = null

    var onClearIconClickListener: (() -> Unit)? = null

    init {
        addRipple()

        updatePadding(left = getDimen(R_ui.dimen.material_padding_16), right = getDimen(R_ui.dimen.material_padding_16))

        withStyledAttributes(
                attributeSet = attrs,
                styleArray = R.styleable.ChooserView
        ) {
            hintText = getString(R.styleable.ChooserView_hintText)?.also {
                binding.primaryTextView.text = it
            }

            defaultIconDrawable = getDrawable(R.styleable.ChooserView_iconDefault)?.also {
                binding.rightIcon.setImageDrawable(it)
            }

            clearIconDrawable = getDrawable(R.styleable.ChooserView_iconClear)
        }

        binding.rightIcon.setOnClickListener {
            if (binding.rightIcon.drawable == clearIconDrawable) {
                onClearIconClickListener?.invoke()
            }
        }
    }

    fun setPrimary(text: String) = binding.setPrimary(text)

    fun reset() = binding.reset()

    private fun ViewChooserBinding.setPrimary(text: String) {
        hintTextView.text = hintText
        primaryTextView.text = text

        rightIcon.setImageDrawable(clearIconDrawable)
        rightIcon.isClickable = true
    }

    private fun ViewChooserBinding.reset() {
        hintTextView.text = null
        primaryTextView.text = hintText

        rightIcon.setImageDrawable(defaultIconDrawable)
        rightIcon.isClickable = false
    }
}