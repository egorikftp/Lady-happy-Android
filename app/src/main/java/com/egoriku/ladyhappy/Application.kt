package com.egoriku.ladyhappy

import android.app.Application
import android.os.Looper
import com.egoriku.core.IApplication
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.ladyhappy.di.AppComponent
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers

open class Application : Application(), IApplication {

    private val appComponent: AppComponent by lazy { AppComponent.init(this@Application) }

    override fun onCreate() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            AndroidSchedulers.from(Looper.getMainLooper(), true)
        }

        super.onCreate()
        appComponent.inject(this)
    }

    override fun getAppComponent(): ApplicationProvider = appComponent
}

