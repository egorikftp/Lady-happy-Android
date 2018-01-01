package com.egoriku.ladyhappy.data.repositories

import com.egoriku.ladyhappy.data.entities.mappers.CategoriesMapper
import com.egoriku.ladyhappy.data.repositories.datasource.CategoriesDataSourceRemote
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository
@Inject constructor(private val categoriesDataSourceRemote: CategoriesDataSourceRemote) {

    fun getCategories(): Observable<CategoriesModel> {
        return categoriesDataSourceRemote
                .getCategories()
                .map({ categoriesDocumentEntity -> CategoriesMapper.transform(categoriesDocumentEntity) })
    }
}