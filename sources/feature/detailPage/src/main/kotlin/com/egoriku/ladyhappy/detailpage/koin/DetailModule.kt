package com.egoriku.ladyhappy.detailpage.koin

import com.egoriku.ladyhappy.detailpage.data.DetailPaginateRepository
import com.egoriku.ladyhappy.detailpage.data.IDetailPaginateRepository
import com.egoriku.ladyhappy.detailpage.domain.usecase.DetailUseCase
import com.egoriku.ladyhappy.detailpage.domain.usecase.IDetailUseCase
import com.egoriku.ladyhappy.detailpage.presentation.DetailPageFragment
import com.egoriku.ladyhappy.detailpage.presentation.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    scope<DetailPageFragment> {
        scoped<IDetailPaginateRepository> { DetailPaginateRepository(firebase = get()) }

        scoped<IDetailUseCase> { DetailUseCase(repository = get()) }

        viewModel { DetailViewModel(detailUseCase = get()) }
    }
}