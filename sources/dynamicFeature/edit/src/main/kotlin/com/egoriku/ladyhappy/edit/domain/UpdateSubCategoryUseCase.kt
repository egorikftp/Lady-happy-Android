package com.egoriku.ladyhappy.edit.domain

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.edit.domain.repository.IUpdateSubCategoryRepository
import com.egoriku.ladyhappy.network.ResultOf

internal class UpdateSubCategoryUseCase(
    private val updateSubCategoryRepository: IUpdateSubCategoryRepository
) : IUpdateSubCategoryUseCase {

    override suspend fun upload(subCategoryModel: SubCategoryModel) =
        updateSubCategoryRepository.upload(subCategoryModel)
}

interface IUpdateSubCategoryUseCase {

    suspend fun upload(subCategoryModel: SubCategoryModel): ResultOf<Void>
}