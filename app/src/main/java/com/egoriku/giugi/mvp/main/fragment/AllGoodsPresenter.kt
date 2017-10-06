package com.egoriku.giugi.mvp.main.fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class AllGoodsPresenter(private val router: Router?) : MvpPresenter<AllGoodsView>() {

    fun onBackPressed() {
        router?.exit()
    }
}
