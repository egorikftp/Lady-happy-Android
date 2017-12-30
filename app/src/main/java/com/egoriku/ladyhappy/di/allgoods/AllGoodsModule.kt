package com.egoriku.ladyhappy.di.allgoods

import android.support.v4.app.Fragment
import com.egoriku.ladyhappy.data.repositories.CategoriesRepository
import com.egoriku.ladyhappy.data.repositories.datasource.CategoriesDataSourceRemote
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsPresenter
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class AllGoodsModule(private val fragment: Fragment) {

    @Provides
    fun providesFragment() = fragment

    @Provides
    fun providesAllGoodsPresenter(useCase: CategoriesUseCase, analyticsInterface: AnalyticsInterface) = AllGoodsPresenter(useCase, analyticsInterface)

    @Provides
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepository) = CategoriesUseCase(categoriesRepository)

    @Provides
    fun provideCategoriesDataSource(firebaseFirestore: FirebaseFirestore) = CategoriesDataSourceRemote(firebaseFirestore)

    @Provides
    fun provideCategoriesRepository(dataSourceRemote: CategoriesDataSourceRemote) = CategoriesRepository(dataSourceRemote)
}