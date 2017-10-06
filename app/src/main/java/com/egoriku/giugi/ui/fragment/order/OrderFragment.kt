package com.egoriku.giugi.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egoriku.corelib_kt.extensions.consume
import com.egoriku.corelib_kt.extensions.inflate
import com.egoriku.giugi.R
import com.egoriku.giugi.mvp.main.fragment.order.OrderPresenter
import com.egoriku.giugi.mvp.main.fragment.order.OrderView
import com.egoriku.giugi.navigation.BackButtonListener
import com.egoriku.giugi.navigation.RouterProvider

class OrderFragment : MvpAppCompatFragment(), OrderView, BackButtonListener {

    companion object {
        fun newInstance(): OrderFragment {
            val fragment = OrderFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: OrderPresenter

    @ProvidePresenter
    fun provideOrderPresenter(): OrderPresenter {
        return OrderPresenter((parentFragment as RouterProvider).getNavigationRouter())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_order, false)
    }

    override fun onBackPressed(): Boolean {
        return consume {
            presenter.onBackPressed()
        }
    }
}
