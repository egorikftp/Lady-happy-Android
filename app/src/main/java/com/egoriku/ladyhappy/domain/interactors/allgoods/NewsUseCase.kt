package com.egoriku.ladyhappy.domain.interactors.allgoods

import com.egoriku.ladyhappy.data.repositories.NewsRepository
import com.egoriku.ladyhappy.domain.interactors.base.BaseUseCase
import com.egoriku.ladyhappy.domain.interactors.base.Params
import javax.inject.Inject

class NewsUseCase
@Inject constructor(private val newsRepository: NewsRepository) : BaseUseCase() {

    override fun getObservable(params: Params?) = newsRepository.getNews()
}