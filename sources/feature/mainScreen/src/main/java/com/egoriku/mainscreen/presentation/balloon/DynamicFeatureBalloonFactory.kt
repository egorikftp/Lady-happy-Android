package com.egoriku.mainscreen.presentation.balloon

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.egoriku.mainscreen.R
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
        setBackgroundColorResource(R.color.RealWhite)
        setBalloonAnimation(BalloonAnimation.FADE)
        setLifecycleOwner(lifecycleOwner)
        dismissWhenTouchOutside = false
        setFocusable(false)
        build()
    }
}
