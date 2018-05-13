package com.egoriku.ladyhappy.common.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder.ofFloat
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageButton
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.LinearLayout
import com.egoriku.corelib_kt.listeners.SimpleAnimationListener
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.data.entities.main.SocialModel

class SocialView : LinearLayout, View.OnClickListener {

    enum class AnimationState {
        NEED_SHOW, NEED_HIDE
    }

    operator fun AnimationState.not() = when (this) {
        AnimationState.NEED_HIDE -> AnimationState.NEED_SHOW
        AnimationState.NEED_SHOW -> AnimationState.NEED_HIDE
    }

    private companion object DefaultValues {
        const val DEFAULT_MAX_PADDING = 30
        const val DEFAULT_ITEM_SIDE_SIZE = 120
        const val ANIMATION_DURATION = 500L
        const val ANIMATION_SHOW_OFFSET = 100L
        const val ANIMATION_HIDE_OFFSET = 50L

        const val VIEW_DEFAULT_POSITION = 0f
        const val VIEW_OFFSET_POSITION = -300f

        const val MIN_ALPHA = 0f
        const val MAX_ALPHA = 1f
    }

    private var animationState = AnimationState.NEED_SHOW

    private lateinit var animatorSet: AnimatorSet

    private var itemPadding = 0
    private var itemSideSize = 0

    private lateinit var onClickListener: (url: String) -> Unit

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SocialView)

        try {
            typedArray.apply {
                itemPadding = getDimensionPixelSize(R.styleable.SocialView_itemPadding, DEFAULT_MAX_PADDING)
                itemSideSize = getDimensionPixelSize(R.styleable.SocialView_itemSideSize, DEFAULT_ITEM_SIDE_SIZE)
            }
        } finally {
            typedArray.recycle()
        }

        animatorSet = AnimatorSet()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (changed) {
            calculateChildViewWidth(right, left)
        }
    }

    private fun calculateChildViewWidth(right: Int, left: Int) {
        if (childCount == 0) {
            return
        }

        val calcItemSideSize = (right - left) / childCount - itemPadding * 2
        Log.d("egorik", "itemSide = $itemSideSize, calc = $calcItemSideSize")

        if (calcItemSideSize < itemSideSize) itemSideSize = calcItemSideSize

        if (firstChild().layoutParams.width != itemSideSize) {
            applyItemWidth()
        }
    }

    private fun applyItemWidth() {
        for (i in 0 until childCount) {
            (getChildAt(i).layoutParams as LinearLayout.LayoutParams).apply {
                width = itemSideSize
                height = itemSideSize
                leftMargin = itemPadding
                rightMargin = itemPadding
            }
        }
    }

    fun showView() {
        if (animatorSet.isRunning) {
            return
        }

        animatorSet = AnimatorSet()

        for (i in 0 until childCount) {
            val childAtPosition = getChildAt(i)
            animatorSet.apply {
                play(getAnimation(childAtPosition, i))
                addListener(changeClickListener(childAtPosition))
                start()
            }
        }

        animateBackgroundColor()

        animationState = animationState.not()
    }

    private fun getAnimation(view: View, viewPosition: Int): Animator {
        return if (animationState == AnimationState.NEED_SHOW) {
            ObjectAnimator.ofPropertyValuesHolder(view,
                    ofFloat(View.ALPHA, MIN_ALPHA, MAX_ALPHA),
                    ofFloat(View.TRANSLATION_Y, VIEW_OFFSET_POSITION, VIEW_DEFAULT_POSITION))
                    .apply {
                        duration = ANIMATION_DURATION
                        startDelay = (ANIMATION_SHOW_OFFSET * viewPosition)
                    }
        } else {
            ObjectAnimator.ofPropertyValuesHolder(view,
                    ofFloat(View.ALPHA, MAX_ALPHA, MIN_ALPHA),
                    ofFloat(View.TRANSLATION_Y, VIEW_DEFAULT_POSITION, VIEW_OFFSET_POSITION))
                    .apply {
                        duration = ANIMATION_DURATION
                        startDelay = (ANIMATION_HIDE_OFFSET * viewPosition)
                    }
        }
    }

    private fun animateBackgroundColor() {
        return if (animationState == AnimationState.NEED_SHOW) {
            background = ColorDrawable(ContextCompat.getColor(context, R.color.RealWhite80))
            val animation = AlphaAnimation(MIN_ALPHA, MAX_ALPHA).apply {
                duration = ANIMATION_DURATION
                fillAfter = true
            }

            startAnimation(animation)

        } else {
            val animation = AlphaAnimation(MAX_ALPHA, MIN_ALPHA).apply {
                duration = ANIMATION_DURATION * 2
                fillAfter = true
                setAnimationListener(object : SimpleAnimationListener() {
                    override fun onAnimationEnd(animation: Animation?) {
                        super.onAnimationEnd(animation)
                        background = ColorDrawable(ContextCompat.getColor(context, R.color.transparent))
                    }
                })
            }

            startAnimation(animation)
        }
    }

    private fun changeClickListener(view: View): AnimatorListenerAdapter {
        return object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.setOnClickListener(if (animationState == AnimationState.NEED_SHOW) this@SocialView else null)
            }
        }
    }

    override fun onClick(v: View) {
        onClickListener(v.tag.toString())
    }

    @SuppressLint("RestrictedApi")
    fun setSocialModel(list: List<SocialModel>) {
        list.forEach { (url, imageResId) ->
            addView(AppCompatImageButton(context).apply {
                alpha = MIN_ALPHA
                tag = url
                scaleType = ImageView.ScaleType.FIT_CENTER
                setImageResource(imageResId)
                setBackgroundResource(R.drawable.bg_social_view)
                supportImageTintList = ContextCompat.getColorStateList(context, R.color.selector_social_icon)
            }, LayoutParams.WRAP_CONTENT)
        }
    }

    fun setOnSocialIconClickListener(onClickListener: (url: String) -> Unit) {
        this.onClickListener = onClickListener
    }
}

fun ViewGroup.firstChild(): View = this.getChildAt(0)
