package com.egoriku.ladyhappy.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.android.material.composethemeadapter.MdcTheme

@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.setThemeContent(
    noinline content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    setContent {
        MdcTheme {
            content()
        }
    }
}