package com.egoriku.ladyhappy.edit.domain

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.edit.domain.repository.IGetSubCategoryRepository

class LoadSubCategoryUseCase(
        private val subCategoryRepository: IGetSubCategoryRepository
) : ILoadSubCategoryUseCase {

    override suspend fun load(documentReference: String) =
            subCategoryRepository.load(documentReference)
}

interface ILoadSubCategoryUseCase {

    suspend fun load(documentReference: String): SubCategoryModel?
}