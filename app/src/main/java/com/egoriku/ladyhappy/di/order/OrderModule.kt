package com.egoriku.ladyhappy.di.order

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.fragment.order.OrderPresenter
import dagger.Module
import dagger.Provides

@Module
class OrderModule {

    @Provides
    fun provideOrderPresenter(analyticsInterface: AnalyticsInterface) = OrderPresenter(analyticsInterface)
}

