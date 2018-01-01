package com.egoriku.ladyhappy.data.repositories

import com.egoriku.ladyhappy.data.entities.mappers.NewsMapper
import com.egoriku.ladyhappy.data.repositories.datasource.NewsDataSourceRemote
import com.egoriku.ladyhappy.domain.models.NewsModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository
@Inject constructor(private val newsDataSourceRemote: NewsDataSourceRemote) {

    fun getNews(): Observable<NewsModel> = newsDataSourceRemote
            .getNews()
            .map({ news -> NewsMapper.transform(news) })
}