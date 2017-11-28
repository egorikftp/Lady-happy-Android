package com.egoriku.ladyhappy.di.order

import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.presentation.ui.fragments.order.OrderFragment
import dagger.Component
import ru.terrakok.cicerone.Router

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {

    fun inject(orderFragment: OrderFragment)

    fun inject(router: Router)
}