package com.egoriku.giugi.ui.activity.fragment


import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.extensions.d
import com.egoriku.corelib_kt.extensions.e
import com.egoriku.corelib_kt.listeners.SimpleAnimatorListener
import com.egoriku.giugi.R
import com.egoriku.giugi.ui.activity.MainActivity
import com.egoriku.giugi.adapter.ToysAdapter
import com.egoriku.giugi.data.Toy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.layout_images.view.*

class OverviewFragment : Fragment() {

    @ColorInt
    private var previousContainerColor = 0

    @ColorInt
    private var previousToolbarColor = 0

    @ColorInt
    private var previousStatusBarColor = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_overview, container, false)

        previousToolbarColor = ContextCompat.getColor(context, R.color.colorPrimary)
        previousStatusBarColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)

        val list = mutableListOf<Toy>()
        list.add(Toy("1", R.drawable.ic1))
        list.add(Toy("2", R.drawable.ic2))
        list.add(Toy("3", R.drawable.ic3))
        list.add(Toy("4", R.drawable.ic4))
        list.add(Toy("5", R.drawable.ic5))
        list.add(Toy("6", R.drawable.ic6))
        list.add(Toy("7", R.drawable.ic7))
        list.add(Toy("8", R.drawable.ic8))

        val adapterToy = ToysAdapter(context, list)

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterToy
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    recyclerView.let {
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        var firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition()
                        val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()

                        d("egor $firstVisiblePosition $lastVisiblePosition")

                        if (firstVisiblePosition == -1 || lastVisiblePosition == -1) {
                            return
                        }

                        val countVisibleItems = lastVisiblePosition - firstVisiblePosition
                        e("egor $countVisibleItems")

                        var overlayDrawable: Bitmap? = null

                        while (firstVisiblePosition <= countVisibleItems) {
                            val visibleView = layoutManager.findViewByPosition(firstVisiblePosition)
                            val drawable = visibleView.image_item.drawable

                            if (drawable != null) {
                                val bitmap = Bitmap.createScaledBitmap((drawable as BitmapDrawable).bitmap, 120, 120, false)

                                overlayDrawable = if (overlayDrawable == null) {
                                    e("egor initial")
                                    bitmap
                                } else {
                                    e("egor add new bitmap")
                                    mergeBitmap(overlayDrawable, bitmap)
                                }
                            }

                            firstVisiblePosition++
                        }

                        overlayDrawable?.let {
                            e("egor pallete")

                            Palette.from(it).generate { palette ->
                                palette.mutedSwatch?.rgb?.let {
                                    changeColorRootLayout(it)

                                    val colorActionBar = darken(it, 0.9)
                                    changeActionBarColor(colorActionBar)

                                    val colorStatusBar = darken(it, 0.7)
                                    changeStatusBarColor(colorStatusBar)
                                }
                            }
                        }
                    }
                }
            })
        }
        return view
    }

    private fun changeColorRootLayout(@ColorInt rootLayoutColor: Int) {
        ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener {
                val position = it.animatedFraction
                val blendedColorRootLayout = blendColors(previousContainerColor, rootLayoutColor, position)

                root_layout.setBackgroundColor(blendedColorRootLayout)
            }

            addListener(object : SimpleAnimatorListener() {
                override fun onAnimationEnd(p0: Animator?) {
                    previousContainerColor = rootLayoutColor
                }
            })
            duration = 1000
            start()
        }
    }

    private fun changeActionBarColor(@ColorInt newColor: Int) {
        ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener {
                val position = it.animatedFraction
                val blendedColor = blendColors(previousToolbarColor, newColor, position)

                (activity as MainActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(blendedColor))
            }

            addListener(object : SimpleAnimatorListener() {
                override fun onAnimationEnd(p0: Animator?) {
                    previousToolbarColor = newColor
                }
            })

            duration = 1000
            start()
        }
    }

    private fun changeStatusBarColor(@ColorInt newColor: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ValueAnimator.ofFloat(0f, 1f).apply {
                addUpdateListener {
                    val position = it.animatedFraction
                    val blendedColor = blendColors(previousStatusBarColor, newColor, position)

                    activity.window.statusBarColor = blendedColor
                }

                addListener(object : SimpleAnimatorListener() {
                    override fun onAnimationEnd(p0: Animator?) {
                        previousStatusBarColor = newColor
                    }
                })

                duration = 1000
                start()
            }
        }
    }

    private fun blendColors(from: Int, to: Int, ratio: Float): Int {
        val inverseRatio = 1f - ratio

        val r = Color.red(to) * ratio + Color.red(from) * inverseRatio
        val g = Color.green(to) * ratio + Color.green(from) * inverseRatio
        val b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio

        return Color.rgb(r.toInt(), g.toInt(), b.toInt())
    }

    fun crimp(c: Int): Int {
        return Math.min(Math.max(c, 0), 255)
    }

    fun darken(color: Int, factor: Double): Int {
        return color and 0xFF000000.toInt() or
                (crimp(((color shr 16 and 0xFF) * factor).toInt()) shl 16) or
                (crimp(((color shr 8 and 0xFF) * factor).toInt()) shl 8) or
                crimp(((color and 0xFF) * factor).toInt())
    }

    fun mergeBitmap(baseBitmap: Bitmap, additionalBitmap: Bitmap): Bitmap {
        val width = baseBitmap.width
        val height = baseBitmap.height + width

        val mergedBitmap = Bitmap.createBitmap(width, height, baseBitmap.config)
        val canvas = Canvas(mergedBitmap)

        canvas.drawBitmap(baseBitmap, 0f, 0f, null)
        canvas.drawBitmap(additionalBitmap, 0f, baseBitmap.height.toFloat(), null)

        return mergedBitmap
    }


    companion object {

        fun newInstance(): OverviewFragment {
            val fragment = OverviewFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
