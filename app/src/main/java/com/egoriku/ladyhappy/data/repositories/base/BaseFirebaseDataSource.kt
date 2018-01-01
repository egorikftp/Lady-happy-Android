package com.egoriku.ladyhappy.data.repositories.base

@Suppress("PropertyName")
open class BaseFirebaseDataSource {

    companion object {
        const val COLLECTION_KEY_CATEGORIES = "categories"
        const val COLLECTION_KEY_NEWS = "news"

        const val QUERY_ID = "id"
        const val QUERY_DATE = "date"
    }
}