package com.egoriku.giugi

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class HelloWorldPresenter : MvpPresenter<HelloWorldView>() {

    init {
        viewState.showMessage(R.string.app_name)
    }
}
