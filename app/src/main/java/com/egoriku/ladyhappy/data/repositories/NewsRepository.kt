package com.egoriku.ladyhappy.data.repositories

import com.egoriku.ladyhappy.data.entities.NewsDocumentEntity
import com.egoriku.ladyhappy.data.repositories.datasource.NewsDataSourceRemote
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository
@Inject constructor(private val newsDataSourceRemote: NewsDataSourceRemote){

    fun getNews(): Observable<NewsDocumentEntity> = newsDataSourceRemote
            .getNews()
}