package com.egoriku.ladyhappy.di.allgoods

import com.egoriku.ladyhappy.data.repositories.CategoriesRepository
import com.egoriku.ladyhappy.data.repositories.NewsRepository
import com.egoriku.ladyhappy.data.repositories.datasource.CategoriesDataSourceRemote
import com.egoriku.ladyhappy.data.repositories.datasource.NewsDataSourceRemote
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.domain.interactors.allgoods.NewsUseCase
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsContract
import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsPresenter
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class AllGoodsModule {

    @Provides
    fun providesAllGoodsPresenter(
            useCase: CategoriesUseCase,
            newsUseCase: NewsUseCase,
            analyticsInterface: AnalyticsInterface
    ): AllGoodsContract.Presenter = AllGoodsPresenter(useCase, newsUseCase, analyticsInterface)

    @Provides
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepository) = CategoriesUseCase(categoriesRepository)

    @Provides
    fun provideNewsUseCase(newsRepository: NewsRepository) = NewsUseCase(newsRepository)

    @Provides
    fun provideCategoriesDataSource(firebaseFirestore: FirebaseFirestore) = CategoriesDataSourceRemote(firebaseFirestore)

    @Provides
    fun provideNewsDataSource(firebaseFirestore: FirebaseFirestore) = NewsDataSourceRemote(firebaseFirestore)

    @Provides
    fun provideCategoriesRepository(dataSourceRemote: CategoriesDataSourceRemote) = CategoriesRepository(dataSourceRemote)

    @Provides
    fun provideNewsRepository(newsDataSourceRemote: NewsDataSourceRemote) = NewsRepository(newsDataSourceRemote)
}