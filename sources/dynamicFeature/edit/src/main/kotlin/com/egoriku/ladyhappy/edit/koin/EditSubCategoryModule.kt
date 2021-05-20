package com.egoriku.ladyhappy.edit.koin

import com.egoriku.ladyhappy.edit.data.datasource.GetSubCategoryDataSource
import com.egoriku.ladyhappy.edit.data.datasource.UpdateSubCategoryDataSource
import com.egoriku.ladyhappy.edit.data.repository.GetSubCategoryRepository
import com.egoriku.ladyhappy.edit.data.repository.UpdateSubCategoryRepository
import com.egoriku.ladyhappy.edit.domain.ILoadSubCategoryUseCase
import com.egoriku.ladyhappy.edit.domain.IUpdateSubCategoryUseCase
import com.egoriku.ladyhappy.edit.domain.LoadSubCategoryUseCase
import com.egoriku.ladyhappy.edit.domain.UpdateSubCategoryUseCase
import com.egoriku.ladyhappy.edit.domain.repository.IGetSubCategoryRepository
import com.egoriku.ladyhappy.edit.domain.repository.IUpdateSubCategoryRepository
import com.egoriku.ladyhappy.edit.presentation.EditFragment
import com.egoriku.ladyhappy.edit.presentation.EditSubCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editModule = module {

    scope<EditFragment> {
        scoped { GetSubCategoryDataSource(firebase = get()) }
        scoped { UpdateSubCategoryDataSource(firebase = get()) }

        scoped<IGetSubCategoryRepository> {
            GetSubCategoryRepository(
                getSubCategoryDataSource = get(),
                stringResource = get()
            )
        }
        scoped<IUpdateSubCategoryRepository> {
            UpdateSubCategoryRepository(
                updateSubCategoryDataSource = get()
            )
        }

        scoped<ILoadSubCategoryUseCase> { LoadSubCategoryUseCase(subCategoryRepository = get()) }
        scoped<IUpdateSubCategoryUseCase> { UpdateSubCategoryUseCase(updateSubCategoryRepository = get()) }

        viewModel { (documentReference: String) ->
            EditSubCategoryViewModel(
                documentReference = documentReference,
                loadSubCategoryUseCase = get(),
                updateSubCategoryUseCase = get()
            )
        }
    }
}