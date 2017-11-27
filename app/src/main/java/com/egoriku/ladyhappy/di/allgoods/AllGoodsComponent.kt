package com.egoriku.ladyhappy.di.allgoods

import com.egoriku.ladyhappy.data.repositories.CategoriesRepository
import com.egoriku.ladyhappy.data.repositories.datasource.CategoriesDataSourceRemote
import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.presentation.presenters.impl.AllGoodsPresenter
import com.egoriku.ladyhappy.presentation.ui.fragments.AllGoodsFragment
import dagger.Component
import ru.terrakok.cicerone.Router

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(AllGoodsModule::class))
public interface AllGoodsComponent {

    //fragments
    fun inject(allGoodsFragment: AllGoodsFragment)

    //presenters
    fun inject(allGoodsPresenter: AllGoodsPresenter)

    // UseCases/Interactors
    fun inject(getCategoriesUseCase: CategoriesUseCase)

    // Repositories
    fun inject(categoriesRepository: CategoriesRepository)

    // DataSources
    fun inject(categoriesDataSourceRemote: CategoriesDataSourceRemote)

    //navigation
    fun inject(router: Router)
}