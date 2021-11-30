package com.egoriku.ladyhappy.postcreator.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.egoriku.ladyhappy.auth.permission.IUserPermission
import com.egoriku.ladyhappy.compose.ui.setThemeContent
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_POST_CREATOR_EXTRA
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.extensions.setFragmentResultListenerWrapper
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.koin.postModule
import com.egoriku.ladyhappy.postcreator.presentation.dialog.color.ColorDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialog.datepicker.CreationDatePicker
import com.egoriku.ladyhappy.postcreator.presentation.screen.ProgressScreen
import com.egoriku.ladyhappy.postcreator.presentation.screen.UploadingScreen
import com.egoriku.ladyhappy.postcreator.presentation.state.Effect
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

const val KEY_CHOOSER_FRAGMENT_RESULT = "chooserKey"
const val KEY_FRAGMENT_RESULT_BUNDLE = "bundleKey"

class PostCreatorFragment : ScopeFragment() {

    init {
        loadKoinModules(postModule)
    }

    private val userPermission: IUserPermission by inject()

    private val viewModel by viewModel<PostViewModel>()

    private val postCreatorParams by extraNotNull<PostCreatorParams>(KEY_POST_CREATOR_EXTRA)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setThemeContent {
        Surface {
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(viewModel) {
                viewModel.effects.collect { effect ->
                    when (effect) {
                        is Effect.UploadError -> toast(getString(R.string.post_creator_upload_error))
                        is Effect.UploadSuccess -> {
                            toast(getString(R.string.post_creator_upload_success))
                            requireActivity().onBackPressed()
                        }
                        is Effect.DemoMode -> toast(getString(R.string.post_creator_demo_mode))
                    }
                }
            }

            when (val state = uiState) {
                is ScreenState.Loading -> ProgressScreen()
                is ScreenState.CreatePost -> PublishProductScreen(
                    viewModel = viewModel,
                    isDemoMode = userPermission.isDemoMode,
                    onBack = { requireActivity().onBackPressed() },
                    openDatePicker = {
                        CreationDatePicker().getDatePicker().show(childFragmentManager, null)
                    },
                    openColorPicker = {
                        ColorDialog().show(childFragmentManager, null)
                    })
                is ScreenState.Uploading -> UploadingScreen(uiState = state)
                else -> Unit
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeForFragmentResult()
        extractImagesFromExtra()
    }

    private fun extractImagesFromExtra() {
        viewModel.processImageResult(postCreatorParams.images.asReversed())
    }

    private fun subscribeForFragmentResult() {
        childFragmentManager.setFragmentResultListenerWrapper(
            requestKey = KEY_CHOOSER_FRAGMENT_RESULT,
            lifecycleOwner = viewLifecycleOwner
        ) { _, result ->
            when (val dialogResult =
                result.getParcelable<DialogResult>(KEY_FRAGMENT_RESULT_BUNDLE)) {
                is DialogResult.Color -> viewModel.setColor(dialogResult.hatColorIds)
                is DialogResult.CreationDate -> viewModel.setDate(dialogResult.dateInMilliseconds)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(postModule)
    }
}