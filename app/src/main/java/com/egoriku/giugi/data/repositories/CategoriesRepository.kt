package com.egoriku.giugi.data.repositories

import com.egoriku.giugi.data.entities.CategoriesEntity
import com.egoriku.giugi.data.entities.mappers.CategoriesMapper
import com.egoriku.giugi.data.repositories.datasource.CategoriesDataSourceRemote
import com.egoriku.giugi.domain.models.CategoriesModel
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository
@Inject constructor(val categoriesDataSourceRemote: CategoriesDataSourceRemote) {

    fun getCategories(): Observable<CategoriesModel>? {
        return categoriesDataSourceRemote
                .getCategories()
                ?.map(Function<CategoriesEntity, CategoriesModel> {
                    entity ->
                    CategoriesMapper.transform(entity))
                }

    }


}