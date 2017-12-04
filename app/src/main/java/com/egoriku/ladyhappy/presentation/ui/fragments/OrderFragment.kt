package com.egoriku.ladyhappy.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.extensions.inflate
import com.egoriku.ladyhappy.App
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.di.order.DaggerOrderComponent
import com.egoriku.ladyhappy.di.order.OrderComponent
import com.egoriku.ladyhappy.di.order.OrderModule
import com.egoriku.ladyhappy.presentation.presenters.OrderMVP
import com.egoriku.ladyhappy.presentation.presenters.impl.OrderPresenter
import com.egoriku.ladyhappy.presentation.ui.activity.MainActivity
import com.egoriku.ladyhappy.presentation.ui.base.BaseFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class OrderFragment : BaseFragment(), OrderMVP.View {

    companion object {
        fun newInstance(): OrderFragment {
            return OrderFragment()
        }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenter: OrderPresenter

    private lateinit var component: OrderComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgs(savedInstanceState)
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
        attachToPresenter()
        super.onAttach(context)
    }

    override fun injectDependencies() {
        component = DaggerOrderComponent.builder()
                .appComponent(App.instance.appComponent)
                .orderModule(OrderModule())
                .build()
        component.inject(this)
    }

    override fun attachToPresenter() {
        presenter.attachView(this)
    }

    override fun detachFromPresenter() {
        presenter.detachView()
    }

    override fun onLandscape() {
    }

    override fun onPortrait() {
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

    override fun getArgs(_bundle: Bundle?) {

    }
}