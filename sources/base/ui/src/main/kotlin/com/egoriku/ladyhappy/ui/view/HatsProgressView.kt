package com.egoriku.ladyhappy.ui.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.egoriku.ladyhappy.extensions.fromApi
import com.egoriku.ladyhappy.extensions.toApi
import com.egoriku.ladyhappy.ui.R

class HatsProgressView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var animatedVectorDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_hats_animation)

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

    init {
        setImageDrawable(animatedVectorDrawable)
    }

    fun startAnimation() {
        animatedVectorDrawable?.registerAnimationCallback(animationCallback)
        animatedVectorDrawable?.start()
    }

    fun stopAnimation() {
        fromApi(Build.VERSION_CODES.N_MR1) {
            animatedVectorDrawable?.stop()
            animatedVectorDrawable?.unregisterAnimationCallback(animationCallback)
        }

        toApi(Build.VERSION_CODES.N_MR1) {
            post { animatedVectorDrawable?.stop() }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        unscheduleDrawable(animatedVectorDrawable)
        animatedVectorDrawable?.clearAnimationCallbacks()
        animatedVectorDrawable?.callback = null
        animatedVectorDrawable = null
    }
}
