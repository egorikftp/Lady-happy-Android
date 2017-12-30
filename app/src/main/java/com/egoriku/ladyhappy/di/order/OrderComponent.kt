package com.egoriku.ladyhappy.di.order

import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.presentation.fragment.order.OrderFragment
import dagger.Component
import ru.terrakok.cicerone.Router

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [OrderModule::class])
interface OrderComponent {

    fun inject(orderFragment: OrderFragment)

    fun inject(router: Router)
}