package com.egoriku.ladyhappy.presentation.fragment.order

import android.os.Bundle
import android.view.View
import com.egoriku.featureactivitymain.presentation.activity.MainActivity
import com.egoriku.ladyhappy.R
import com.egoriku.ui.BaseInjectableFragment
import javax.inject.Inject

class OrderFragment : BaseInjectableFragment<OrderContract.View, OrderContract.Presenter>(), OrderContract.View {

    override fun injectDependencies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() = OrderFragment()
    }

    @Inject
    lateinit var orderPresenter: OrderContract.Presenter

    override fun initPresenter() = orderPresenter

    override fun provideLayout(): Int = R.layout.fragment_order

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTitle(R.string.navigation_drawer_order)
    }

    override fun showTitle(title: Int) {
        (activity as MainActivity).setUpToolbar(title)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}