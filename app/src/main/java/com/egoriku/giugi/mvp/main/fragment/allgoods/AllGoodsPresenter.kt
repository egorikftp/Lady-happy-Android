package com.egoriku.giugi.mvp.main.fragment.allgoods

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class AllGoodsPresenter(private val router: Router?, title: String) : MvpPresenter<AllGoodsView>() {

    init {
        viewState.setTitle(title)
    }

    fun onBackPressed() {
        router?.exit()
    }
}
