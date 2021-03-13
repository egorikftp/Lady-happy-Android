package com.egoriku.ladyhappy.catalog.edit.koin

import com.egoriku.ladyhappy.catalog.edit.data.GetSubCategoryDataSource
import com.egoriku.ladyhappy.catalog.edit.data.IGetSubCategoryDataSource
import com.egoriku.ladyhappy.catalog.edit.data.IUpdateSubCategoryDataSource
import com.egoriku.ladyhappy.catalog.edit.data.UpdateSubCategoryDataSource
import com.egoriku.ladyhappy.catalog.edit.domain.ILoadSubCategoryUseCase
import com.egoriku.ladyhappy.catalog.edit.domain.IUploadSubCategoryUseCase
import com.egoriku.ladyhappy.catalog.edit.domain.LoadSubCategoryUseCase
import com.egoriku.ladyhappy.catalog.edit.domain.UploadSubCategoryUseCase
import com.egoriku.ladyhappy.catalog.edit.presentation.EditSubCategoryFragment
import com.egoriku.ladyhappy.catalog.edit.presentation.EditSubCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editSubCategoryModule = module {

    scope<EditSubCategoryFragment> {
        scoped<IGetSubCategoryDataSource> { GetSubCategoryDataSource(firebase = get()) }
        scoped<IUpdateSubCategoryDataSource> { UpdateSubCategoryDataSource(firebase = get()) }

        scoped<ILoadSubCategoryUseCase> { LoadSubCategoryUseCase(getSubCategoryDataSource = get()) }
        scoped<IUploadSubCategoryUseCase> { UploadSubCategoryUseCase(updateSubCategoryDataSource = get()) }

        viewModel { (documentReference: String) ->
            EditSubCategoryViewModel(
                    documentReference = documentReference,
                    loadSubCategoryUseCase = get(),
                    uploadSubCategoryUseCase = get()
            )
        }
    }
}