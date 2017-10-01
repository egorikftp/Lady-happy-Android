package com.egoriku.giugi.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.egoriku.giugi.common.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class DrawerNavigationPresenter(private val router: Router): MvpPresenter<DrawerNavigationView>() {

    fun onAllGoodsDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.ALL_GOODS_POSITION)
        router.replaceScreen(Screens.ALL_GOODS_SCREEN)
    }

    fun onOrderDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.ORDER_POSITION)
        router.replaceScreen(Screens.ORDER_SCREEN)
    }

    fun onShareDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.SHARE_POSITION)
        router.replaceScreen(Screens.SHARE_SCREEN)
    }

    fun onFeedbackDrawerItemClick(){
        viewState.selectDrawerItem(DrawerNavigationView.FEEDBACK_POSITION)
        router.replaceScreen(Screens.FEEDBACK_SCREEN)
    }

    fun onBackPressed(){
        router.exit()
    }
}
