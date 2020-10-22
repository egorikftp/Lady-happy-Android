package com.egoriku.ladyhappy.extensions.listeners

import android.view.animation.Animation

abstract class SimpleAnimationListener : Animation.AnimationListener {

    override fun onAnimationRepeat(animation: Animation?) = Unit

    override fun onAnimationEnd(animation: Animation?) = Unit

    override fun onAnimationStart(animation: Animation?) = Unit
}
