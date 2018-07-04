package com.egoriku.mainfragment.presentation.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.egoriku.corelib_kt.dsl.hide
import com.egoriku.corelib_kt.dsl.show
import com.egoriku.ui.common.parallax.ParallaxScrollListener
import com.egoriku.mainfragment.R
import com.egoriku.mainfragment.data.entities.OurTeamEntity
import com.egoriku.mainfragment.data.entities.SocialModel
import com.egoriku.mainfragment.data.entities.TeamMember
import com.egoriku.mainfragment.presentation.fragment.controller.*
import com.egoriku.ui.BaseInjectableFragment
import kotlinx.android.synthetic.main.fragment_main_page.*
import org.jetbrains.anko.support.v4.browse
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class MainPageFragment : BaseInjectableFragment<MainPageContract.View, MainPageContract.Presenter>(), MainPageContract.View {

    //  @Inject
    lateinit var mainPagePresenter: MainPageContract.Presenter

    private val mainPageAdapter = EasyAdapter()
    private lateinit var parallaxScrollListener: ParallaxScrollListener

    private lateinit var headerController: HeaderController
    private lateinit var aboutController: AboutController
    private lateinit var quotasController: QuotesController
    private lateinit var sectionsHeaderController: SectionsHeaderController
    private lateinit var ourTeamController: OurTeamController

    companion object {
        fun newInstance() = MainPageFragment()
    }

    override fun provideLayout(): Int = R.layout.fragment_main_page

    override fun initPresenter(): MainPageContract.Presenter = mainPagePresenter

    override fun injectDependencies() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     //   activity?.cast<MainActivity>()?.setUpToolbar(R.string.navigation_main)
        initViews()
    }

    override fun initViews() {

        mainPageRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainPageAdapter
            setHasFixedSize(true)
        }

        parallaxScrollListener = ParallaxScrollListener().apply {
            initWith(mainPageRecyclerView, lifecycle)
        }

        headerController = HeaderController()
        aboutController = AboutController()
        sectionsHeaderController = SectionsHeaderController()
        quotasController = QuotesController(parallaxScrollListener)
        ourTeamController = OurTeamController(onSocialItemClick = {
            browse(it)
        })

        showInformation()
    }

    override fun showLoading() {
        mainProgressBar.show()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
    }

    override fun showInformation() {
        val s = "Женщина должна притягивать своей красотой взгляды окружающих. Красивый аксессуар всегда был неотъемлемой частью гардероба. Он подчеркивал женскую загадочность и робкость... \n" +
                "\n" +
                "В последнее время изделия из натуральных материалов стали популярны на рынке. Все обусловленно тем, что они делаются вручную и, в конечном итоге, являются уникальным предметом. Компания \"Дамское счастье\" сделает все, чтобы вы почувствовали себя той самой неповторимой."

        val quotes = "Главное в платье - это женщина, которая его надевает."

        val ourTeamEntity = OurTeamEntity(listOf(
                TeamMember("https://lady-happy.com/assets/images/team-1.jpg",
                        "Ольга Урбанович",
                        "Мастер",
                        listOf(
                                SocialModel("https://vk.com/urbanovich.olga", R.drawable.ic_vk),
                                SocialModel("https://ok.ru/urbanovich.olga", R.drawable.ic_odnoklassniki),
                                SocialModel("https://www.instagram.com/urbanovich.olga/", R.drawable.ic_instagram),
                                SocialModel("https://t.me/urbanovich_olga/", R.drawable.ic_telegram)
                        )),
                TeamMember("https://lady-happy.com/assets/images/team-2.jpg",
                        "Егор Урбанович",
                        "Разработчик / UX дизайнейр / Фотограф",
                        listOf(
                                SocialModel("https://vk.com/egoriku", R.drawable.ic_vk),
                                SocialModel("https://t.me/egoriku/", R.drawable.ic_telegram),
                                SocialModel("https://www.instagram.com/egorik.u//", R.drawable.ic_instagram),
                                SocialModel("https://github.com/egorikftp/", R.drawable.ic_github),
                                SocialModel("https://github.com/egorikftp/", R.drawable.ic_github)
                        )))
        )

        mainPageAdapter.setItems(
                ItemList.create()
                        .addIf(true, headerController)
                        .add(s, aboutController)
                        .addIf(true, getString(R.string.adapter_item_header_quotes), sectionsHeaderController)
                        .add(quotes, quotasController)
                        .addIf(ourTeamEntity.ourTeam.isNotEmpty(), getString(R.string.adapter_item_header_our_team), sectionsHeaderController)
                        .addAll(ourTeamEntity.ourTeam, ourTeamController)
        )
    }
}