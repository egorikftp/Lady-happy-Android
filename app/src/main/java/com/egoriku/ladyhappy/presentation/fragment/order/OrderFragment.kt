package com.egoriku.ladyhappy.presentation.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.dsl.inflate
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.base.BaseInjectableFragment
import javax.inject.Inject

class OrderFragment : BaseInjectableFragment<OrderContract.View, OrderContract.Presenter>(), OrderContract.View {

    companion object {
        fun newInstance() = OrderFragment()
    }

    @Inject
    lateinit var orderPresenter: OrderContract.Presenter

    override fun initPresenter()  = orderPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_order, false)
    }

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