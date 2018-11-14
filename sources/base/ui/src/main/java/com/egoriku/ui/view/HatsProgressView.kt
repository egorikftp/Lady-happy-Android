package com.egoriku.ui.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.egoriku.ui.R
import com.egoriku.ui.ktx.fromApi
import com.egoriku.ui.ktx.toApi

class HatsProgressView : ImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val animatedVectorDrawable: AnimatedVectorDrawableCompat?

    init {
        animatedVectorDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_hats_animation)
        setImageDrawable(animatedVectorDrawable)
    }

    private val animationCallback = object : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(dr: Drawable?) {
            fromApi(Build.VERSION_CODES.N_MR1) {
                animatedVectorDrawable?.start()
            }

            toApi(Build.VERSION_CODES.N_MR1) {
                post(null)
            }
        }
    }

    fun show() {
        animatedVectorDrawable?.registerAnimationCallback(animationCallback)
        animatedVectorDrawable?.start()
    }

    fun hide() {
        fromApi(Build.VERSION_CODES.N_MR1) {
            animatedVectorDrawable?.stop()
            animatedVectorDrawable?.unregisterAnimationCallback(animationCallback)
        }

        toApi(Build.VERSION_CODES.N_MR1) {
            post { animatedVectorDrawable?.stop() }
        }
    }
}
