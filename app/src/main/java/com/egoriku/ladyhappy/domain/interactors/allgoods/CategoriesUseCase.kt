package com.egoriku.ladyhappy.domain.interactors.allgoods

import com.egoriku.ladyhappy.data.repositories.CategoriesRepository
import com.egoriku.ladyhappy.domain.interactors.base.BaseUseCase
import com.egoriku.ladyhappy.domain.interactors.base.Params
import javax.inject.Inject

class CategoriesUseCase
@Inject constructor(private val categoriesRepository: CategoriesRepository) : BaseUseCase() {

    override fun getObservable(params: Params) = categoriesRepository.getCategories()
}