package com.egoriku.ladyhappy.presentation.activity.newpost

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.egoriku.corelib_kt.dsl.colorCompat
import com.egoriku.corelib_kt.dsl.fromApi
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.domain.models.SingleCategoryModel
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsFragment
import kotlinx.android.synthetic.main.activity_detail_category.*

class DetailCategoryActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        setSupportActionBar(toolbarDetailCategoryActivity)

        supportPostponeEnterTransition()

        val extras = intent.extras
        val categoryModel: SingleCategoryModel? = extras.getParcelable(AllGoodsFragment.EXTRA_ANIMAL_ITEM)

        supportActionBar?.apply {
            title = categoryModel?.title
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        setTranslucentStatusBar()

        val imageUrl = categoryModel!!.imageUrl

        fromApi(Build.VERSION_CODES.LOLLIPOP, true) {
            detailImageView.transitionName = extras.getString(AllGoodsFragment.EXTRA_ANIMAL_IMAGE_TRANSITION_NAME)
        }

        Glide.with(this)
                .load(imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(detailImageView)
    }

    fun setTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslucentStatusBarLollipop()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatusBarKiKat()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setTranslucentStatusBarLollipop() {
        window.statusBarColor = colorCompat(R.color.transpanent)
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun setTranslucentStatusBarKiKat() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}
