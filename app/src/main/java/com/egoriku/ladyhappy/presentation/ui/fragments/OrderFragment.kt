package com.egoriku.ladyhappy.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.arch.BaseFragment
import com.egoriku.corelib_kt.dsl.inflate
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.di.order.DaggerOrderComponent
import com.egoriku.ladyhappy.di.order.OrderComponent
import com.egoriku.ladyhappy.di.order.OrderModule
import com.egoriku.ladyhappy.presentation.presenters.OrderContract
import com.egoriku.ladyhappy.presentation.presenters.impl.OrderPresenter
import com.egoriku.ladyhappy.presentation.ui.activity.MainActivity
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class OrderFragment : BaseFragment<OrderContract.View, OrderContract.Presenter>(), OrderContract.View {

    companion object {
        fun newInstance(): OrderFragment {
            return OrderFragment()
        }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var orderPresenter: OrderPresenter

    private lateinit var component: OrderComponent

    override fun initPresenter(): OrderContract.Presenter {
        return orderPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_order, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTitle(R.string.navigation_drawer_order)
    }

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun injectDependencies() {
        component = DaggerOrderComponent.builder()
                .appComponent(App.instance.appComponent)
                .orderModule(OrderModule())
                .build()
        component.inject(this)
    }

    override fun showTitle(title: Int) {
        (activity as MainActivity).setUpToolbar(title)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {

    }

    override fun showNoNetwork() {

    }
}