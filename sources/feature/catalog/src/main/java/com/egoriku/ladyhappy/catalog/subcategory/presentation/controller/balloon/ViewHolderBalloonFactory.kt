package com.egoriku.ladyhappy.catalog.subcategory.presentation.controller.balloon

import android.content.Context
import android.graphics.Typeface
import androidx.lifecycle.LifecycleOwner
import com.egoriku.ladyhappy.catalog.R
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon

private const val ALPHA = 0.9f
private const val ARROW_SIZE = 7
private const val CORNER_RADIUS = 4f
private const val HEIGHT = 40
private const val PADDING = 8

class ViewHolderBalloonFactory : Balloon.Factory() {

    override fun create(
            context: Context,
            lifecycle: LifecycleOwner?
    ): Balloon = createBalloon(context) {
        setPaddingRight(PADDING)
        setPaddingLeft(PADDING)
        setArrowSize(ARROW_SIZE)
        setHeight(HEIGHT)
        setCornerRadius(CORNER_RADIUS)
        setAlpha(ALPHA)
        textTypeface = Typeface.BOLD
        setTextResource(R.string.catalog_trending_hint)
        setTextColorResource(R.color.RealBlack)
        setBackgroundColorResource(R.color.RealWhite)
        setBalloonAnimation(BalloonAnimation.FADE)
        setLifecycleOwner(lifecycleOwner)
        arrowOrientation = ArrowOrientation.RIGHT
        build()
    }
}
