package com.egoriku.ladyhappy.postcreator.koin

import com.egoriku.ladyhappy.postcreator.presentation.PostViewModel
import com.egoriku.ladyhappy.postcreator.presentation.fragment.PostCreatorFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postModule = module {
    scope<PostCreatorFragment> {
        viewModel {
            PostViewModel()
        }
    }
}