package com.egoriku.giugi.activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.egoriku.corelib_kt.listeners.SimpleAnimatorListener
import com.egoriku.giugi.HelloWorldPresenter
import com.egoriku.giugi.HelloWorldView
import com.egoriku.giugi.R
import com.egoriku.giugi.adapter.ToysAdapter
import com.egoriku.giugi.data.Toy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_images.view.*


class MainActivity : MvpAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HelloWorldView {

    override fun showMessage(message: Int) {
        test_view.text = message.toString()
    }

    @InjectPresenter
    lateinit var presenter: HelloWorldPresenter

    @ColorInt
    private var previousContainerColor = 0

    @ColorInt
    private var previousToolbarColor = 0

    @ColorInt
    private var previousStatusBarColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "All toys"

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        previousToolbarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        previousStatusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        nav_view.apply {
            setNavigationItemSelectedListener(this@MainActivity)
            setCheckedItem(R.id.nav_all_toys)
        }

        val list = mutableListOf<Toy>()
        list.add(Toy("1", R.drawable.ic1))
        list.add(Toy("2", R.drawable.ic2))
        list.add(Toy("3", R.drawable.ic3))
        list.add(Toy("4", R.drawable.ic4))
        list.add(Toy("5", R.drawable.ic5))
        list.add(Toy("6", R.drawable.ic6))
        list.add(Toy("7", R.drawable.ic7))
        list.add(Toy("8", R.drawable.ic8))

        val adapterToy = ToysAdapter(this, list)

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterToy
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    recyclerView.let {
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        var firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition()
                        val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()

                        Log.d("egor", " $firstVisiblePosition $lastVisiblePosition")

                        if (firstVisiblePosition == -1 || lastVisiblePosition == -1) {
                            return
                        }

                        val countVisibleItems = lastVisiblePosition - firstVisiblePosition
                        Log.e("egor", countVisibleItems.toString())

                        var overlayDrawable: Bitmap? = null

                        while (firstVisiblePosition <= countVisibleItems) {
                            val view = layoutManager.findViewByPosition(firstVisiblePosition)
                            val drawable = view.image_item.drawable

                            if (drawable != null) {
                                val bitmap = Bitmap.createScaledBitmap((drawable as BitmapDrawable).bitmap, 120, 120, false)

                                if (overlayDrawable == null) {
                                    Log.e("egor", "initial")
                                    overlayDrawable = bitmap
                                } else {
                                    Log.e("egor", "add new bitmap")
                                    overlayDrawable = mergeBitmap(overlayDrawable, bitmap)
                                }
                            }
                            Log.e("egor", "inc")
                            firstVisiblePosition++
                        }

                        overlayDrawable?.let {
                            Log.e("egor", "pallete")

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

                supportActionBar?.setBackgroundDrawable(ColorDrawable(blendedColor))
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

                    window.statusBarColor = blendedColor
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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }

        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_order -> startActivity(Intent(this, OrderActivity::class.java))
            R.id.nav_share -> Toast.makeText(this, "Will share application link", Toast.LENGTH_SHORT).show()
            R.id.nav_send -> Toast.makeText(this, "Will share feedback in Google Play", Toast.LENGTH_SHORT).show()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
