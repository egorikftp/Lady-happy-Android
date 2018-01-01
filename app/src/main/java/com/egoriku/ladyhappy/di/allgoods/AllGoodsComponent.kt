package com.egoriku.ladyhappy.di.allgoods

import com.egoriku.ladyhappy.data.repositories.CategoriesRepository
import com.egoriku.ladyhappy.data.repositories.NewsRepository
import com.egoriku.ladyhappy.data.repositories.datasource.CategoriesDataSourceRemote
import com.egoriku.ladyhappy.data.repositories.datasource.NewsDataSourceRemote
import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.domain.interactors.allgoods.NewsUseCase
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsPresenter
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsFragment
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [AllGoodsModule::class])
interface AllGoodsComponent {

    fun inject(allGoodsFragment: AllGoodsFragment)
    fun inject(allGoodsPresenter: AllGoodsPresenter)

    fun inject(categoriesUseCase: CategoriesUseCase)
    fun inject(newsUseCase: NewsUseCase)

    fun inject(categoriesRepository: CategoriesRepository)
    fun inject(newsRepository: NewsRepository)

    fun inject(categoriesDataSourceRemote: CategoriesDataSourceRemote)
    fun inject(newsDataSourceRemote: NewsDataSourceRemote)

    fun inject(router: Router)
    fun inject(navigatorHolder: NavigatorHolder)
}