package com.egoriku.ladyhappy.domain.interactors.allgoods

import com.egoriku.core.usecase.BaseUseCase
import com.egoriku.core.usecase.Params
import javax.inject.Inject

class CategoriesUseCase
@Inject constructor(private val categoriesRepository: CategoriesRepository) : BaseUseCase() {

    override fun getObservable(params: Params) = categoriesRepository.getCategories()
}