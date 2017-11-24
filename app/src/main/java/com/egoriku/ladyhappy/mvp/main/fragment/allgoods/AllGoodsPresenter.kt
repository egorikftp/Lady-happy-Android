package com.egoriku.ladyhappy.mvp.main.fragment.allgoods

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class AllGoodsPresenter(private val router: Router?, private val title: String) : MvpPresenter<AllGoodsView>() {

    override fun attachView(view: AllGoodsView?) {
        super.attachView(view)
        viewState.setTitle(title)
    }

    fun onBackPressed() {
        router?.exit()
    }
}
