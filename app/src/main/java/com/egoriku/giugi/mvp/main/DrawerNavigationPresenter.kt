package com.egoriku.giugi.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.egoriku.giugi.common.Fragments
import ru.terrakok.cicerone.Router

@InjectViewState
class DrawerNavigationPresenter(private val router: Router): MvpPresenter<DrawerNavigationView>() {

    fun onAllGoodsDrawerItemClick(){
       // viewState.selectDrawerItem(DrawerNavigationView.OVERVIEW_POSITION)
        router.replaceScreen(Fragments.ALL_GOODS)
    }

    fun onOrderDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.ORDER_POSITION)
        router.replaceScreen(Fragments.ORDER)
    }

    fun onShareDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.SHARE_POSITION)
        router.replaceScreen(Fragments.SHARE)
    }

    fun onFeedbackDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.FEEDBACK_POSITION)
        router.replaceScreen(Fragments.FEEDBACK)
    }

    fun onBackPressed(){
        router.exit()
    }
}
