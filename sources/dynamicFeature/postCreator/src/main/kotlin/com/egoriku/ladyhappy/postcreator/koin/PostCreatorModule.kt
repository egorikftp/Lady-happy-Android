package com.egoriku.ladyhappy.postcreator.koin

import com.egoriku.ladyhappy.postcreator.data.local.CompressImageRepository
import com.egoriku.ladyhappy.postcreator.data.local.CreateFileRepository
import com.egoriku.ladyhappy.postcreator.data.remote.UploadPostImageRepository
import com.egoriku.ladyhappy.postcreator.domain.usecase.UploadImagesUseCase
import com.egoriku.ladyhappy.postcreator.presentation.PostViewModel
import com.egoriku.ladyhappy.postcreator.presentation.fragment.PostCreatorFragment
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postModule = module {

    scope<PostCreatorFragment> {
        scoped { CreateFileRepository(androidContext()) }
        scoped { CompressImageRepository(androidContext()) }
        scoped { UploadPostImageRepository(firebase = get()) }

        scoped {
            UploadImagesUseCase(
                    createFileRepository = get(),
                    compressImageRepository = get(),
                    uploadPostImageRepository = get()
            )
        }

        viewModel {
            PostViewModel(uploadImagesUseCase = get())
        }
    }
}