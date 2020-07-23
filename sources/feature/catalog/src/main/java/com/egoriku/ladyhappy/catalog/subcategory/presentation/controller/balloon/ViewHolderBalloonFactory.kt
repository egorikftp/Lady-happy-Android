package com.egoriku.ladyhappy.catalog.subcategory.presentation.controller.balloon

import android.content.Context
import android.graphics.Typeface
import androidx.lifecycle.LifecycleOwner
import com.egoriku.ladyhappy.catalog.R
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon

class ViewHolderBalloonFactory : Balloon.Factory() {

    override fun create(
            context: Context,
            lifecycle: LifecycleOwner?
    ): Balloon = createBalloon(context) {
        setPaddingRight(8)
        setPaddingLeft(8)
        setArrowSize(7)
        setHeight(40)
        setCornerRadius(4f)
        setAlpha(0.9f)
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
