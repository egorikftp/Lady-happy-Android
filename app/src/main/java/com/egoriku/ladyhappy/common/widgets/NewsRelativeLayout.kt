package com.egoriku.ladyhappy.common.widgets

import android.os.Build.VERSION_CODES
import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout


class NewsRelativeLayout : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @TargetApi(VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, (widthMeasureSpec / 1.5).toInt())
    }
}
