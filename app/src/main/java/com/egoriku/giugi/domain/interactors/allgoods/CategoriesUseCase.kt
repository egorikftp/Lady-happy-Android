package com.egoriku.giugi.domain.interactors.allgoods

import com.egoriku.giugi.data.repositories.CategoriesRepository
import com.egoriku.giugi.domain.interactors.Params
import com.egoriku.giugi.domain.interactors.base.BaseUseCase
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