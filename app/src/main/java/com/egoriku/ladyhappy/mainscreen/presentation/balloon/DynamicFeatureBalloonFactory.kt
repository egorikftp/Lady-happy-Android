package com.egoriku.ladyhappy.mainscreen.presentation.balloon

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon

private const val CORNER_RADIUS = 10f

class DynamicFeatureBalloonFactory : Balloon.Factory() {

    override fun create(
            context: Context,
            lifecycle: LifecycleOwner?
    ): Balloon = createBalloon(context) {
        arrowVisible = false
        layoutRes = R.layout.layout_ballon
        setCornerRadius(CORNER_RADIUS)
        backgroundColor = context.colorFromAttr(R.attr.colorSurface)
        setBalloonAnimation(BalloonAnimation.FADE)
        setLifecycleOwner(lifecycleOwner)
        dismissWhenTouchOutside = false
        setFocusable(false)
        build()
    }
}
