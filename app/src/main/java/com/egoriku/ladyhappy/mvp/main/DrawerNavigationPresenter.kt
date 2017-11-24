package com.egoriku.ladyhappy.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.egoriku.ladyhappy.common.Fragments
import com.egoriku.ladyhappy.common.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class DrawerNavigationPresenter(private val router: Router) : MvpPresenter<DrawerNavigationView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        openAllGoodsCategory()
    }

    fun openAllGoodsCategory() {
        router.replaceScreen(Fragments.ALL_GOODS)
        viewState.selectDrawerItem(DrawerNavigationView.ALL_GOODS_POSITION)
    }

    fun openOrderCategory() {
        router.replaceScreen(Fragments.ORDER)
        viewState.selectDrawerItem(DrawerNavigationView.ORDER_POSITION)
    }

    fun openShareCategory() {
        router.navigateTo(Fragments.SHARE)
        viewState.selectDrawerItem(DrawerNavigationView.SHARE_POSITION)
    }

    fun openFeedbackCategory() {
        router.navigateTo(Fragments.FEEDBACK)
        viewState.selectDrawerItem(DrawerNavigationView.FEEDBACK_POSITION)
    }

    fun openCreateNewPostScreen() {
        router.navigateTo(Screens.CREATE_POST_ACTIVITY)
        viewState.selectDrawerItem(DrawerNavigationView.CREATE_NEW_POST_POSITION)
    }

    fun onBackPressed() {
        router.exit()
    }
}
