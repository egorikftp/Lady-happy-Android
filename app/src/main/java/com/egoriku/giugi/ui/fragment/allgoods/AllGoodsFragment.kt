package com.egoriku.giugi.ui.fragment.allgoods


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egoriku.corelib_kt.extensions.consume
import com.egoriku.corelib_kt.extensions.inflate
import com.egoriku.giugi.App
import com.egoriku.giugi.R
import com.egoriku.giugi.adapter.ToysAdapter
import com.egoriku.giugi.data.Toy
import com.egoriku.giugi.mvp.main.fragment.allgoods.AllGoodsPresenter
import com.egoriku.giugi.mvp.main.fragment.allgoods.AllGoodsView
import com.egoriku.giugi.navigation.BackButtonListener
import com.egoriku.giugi.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_all_goods.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class AllGoodsFragment : MvpAppCompatFragment(), AllGoodsView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: AllGoodsPresenter

    @ProvidePresenter
    fun provideAllGoodsPresenter(): AllGoodsPresenter {
        return AllGoodsPresenter(router, getString(R.string.navigation_drawer_all_goods))
    }

    companion object {
        fun newInstance(): AllGoodsFragment {
            return AllGoodsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_all_goods, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<Toy>()
        list.add(Toy("1", R.drawable.ic1))
        list.add(Toy("2", R.drawable.ic2))
        list.add(Toy("3", R.drawable.ic3))
        list.add(Toy("4", R.drawable.ic4))
        list.add(Toy("5", R.drawable.ic5))
        list.add(Toy("6", R.drawable.ic6))
        list.add(Toy("7", R.drawable.ic7))
        list.add(Toy("8", R.drawable.ic8))

        val adapterToy = ToysAdapter(context, list)

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterToy
        }
    }

    override fun onBackPressed(): Boolean {
        return consume {
            presenter.onBackPressed()
        }
    }

    override fun setTitle(title: String) {
        Log.e("egor", title)
        (activity as MainActivity).onFragmentStart(title)
    }
}
