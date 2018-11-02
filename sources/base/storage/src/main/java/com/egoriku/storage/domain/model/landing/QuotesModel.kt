package com.egoriku.storage.domain.model.landing

import com.egoriku.core.model.IQuotesModel

data class QuotesModel(
        override val quote: String,
        override val author: String
) : IQuotesModel