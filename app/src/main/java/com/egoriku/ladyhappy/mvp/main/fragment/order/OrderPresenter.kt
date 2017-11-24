package com.egoriku.ladyhappy.mvp.main.fragment.order

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class OrderPresenter(private val router: Router?) : MvpPresenter<OrderView>(){

    fun onBackPressed(){
        router?.exit()
    }
}
