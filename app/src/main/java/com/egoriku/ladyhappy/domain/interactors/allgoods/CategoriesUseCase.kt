package com.egoriku.ladyhappy.domain.interactors.allgoods

import com.egoriku.ladyhappy.data.repositories.CategoriesRepository
import com.egoriku.ladyhappy.domain.interactors.Params
import com.egoriku.ladyhappy.domain.interactors.base.BaseUseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Implementation of {@link BaseUseCase} that represents a UseCase/Interactor
 */

class CategoriesUseCase
@Inject constructor(private val categoriesRepository: CategoriesRepository) : BaseUseCase() {

    override fun getObservable(params: Params): Observable<*> {
        return categoriesRepository.getCategories()
    }
}