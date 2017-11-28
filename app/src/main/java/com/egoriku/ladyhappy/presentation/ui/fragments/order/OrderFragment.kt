package com.egoriku.ladyhappy.presentation.ui.fragments.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egoriku.corelib_kt.extensions.consume
import com.egoriku.corelib_kt.extensions.inflate
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.mvp.main.fragment.order.OrderPresenter
import com.egoriku.ladyhappy.mvp.main.fragment.order.OrderView
import com.egoriku.ladyhappy.navigation.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class OrderFragment : MvpAppCompatFragment(), OrderView, BackButtonListener {

    companion object {
        fun newInstance(): OrderFragment {
            return OrderFragment()
        }
    }

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: OrderPresenter

    @ProvidePresenter
    fun provideOrderPresenter(): OrderPresenter {
        return OrderPresenter(router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       // App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_order, false)
    }

    override fun onBackPressed(): Boolean {
        return consume {
            presenter.onBackPressed()
        }
    }
}
