package com.egoriku.ladyhappy.settings.presentation.dialog.base

import androidx.appcompat.app.AppCompatDialogFragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

open class ScopeDialogFragment : AppCompatDialogFragment(), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()
}