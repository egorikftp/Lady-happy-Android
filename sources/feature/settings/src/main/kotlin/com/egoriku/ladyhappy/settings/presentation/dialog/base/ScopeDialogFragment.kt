package com.egoriku.ladyhappy.settings.presentation.dialog.base

import androidx.appcompat.app.AppCompatDialogFragment
import org.koin.androidx.scope.newScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope

open class ScopeDialogFragment : AppCompatDialogFragment(), KoinScopeComponent {

    override val scope: Scope by lazy { newScope(this) }

    override fun onDestroy() {
        closeScope()
        super.onDestroy()
    }

    /**
     * inject lazily
     * @param qualifier - bean qualifier / optional
     * @param mode
     * @param parameters - injection parameters
     */
    inline fun <reified T : Any> KoinScopeComponent.inject(
        qualifier: Qualifier? = null,
        mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
        noinline parameters: ParametersDefinition? = null
    ) = lazy(mode) { get<T>(qualifier, parameters) }

    /**
     * get given dependency
     * @param name - bean name
     * @param scope
     * @param parameters - injection parameters
     */
    inline fun <reified T : Any> KoinScopeComponent.get(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
    ): T = scope.get(qualifier, parameters)
}