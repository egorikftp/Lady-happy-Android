package com.egoriku.ladyhappy.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> Fragment.viewBindingLifecycle(
        bindUntilEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): ReadWriteProperty<Fragment, T> =
        object : ReadWriteProperty<Fragment, T>, LifecycleObserver {

            private var binding: T? = null

            private var viewLifecycleOwner: LifecycleOwner? = null

            init {
                this@viewBindingLifecycle
                        .viewLifecycleOwnerLiveData
                        .observe(this@viewBindingLifecycle, Observer { newLifecycleOwner ->
                            viewLifecycleOwner
                                    ?.lifecycle
                                    ?.removeObserver(this)

                            viewLifecycleOwner = newLifecycleOwner.also {
                                it.lifecycle.addObserver(this)
                            }
                        })
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onDestroy(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
                if (event == bindUntilEvent) {
                    binding = null
                }
            }

            override fun getValue(
                    thisRef: Fragment,
                    property: KProperty<*>
            ): T {
                return this.binding!!
            }

            override fun setValue(
                    thisRef: Fragment,
                    property: KProperty<*>,
                    value: T
            ) {
                this.binding = value
            }
        }