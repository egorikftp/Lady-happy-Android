package com.egoriku.ladyhappy.postcreator.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.setFragmentResultListenerWrapper
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.FragmentPostCreatorBinding
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.koin.postModule
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.CategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.ColorDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.SubCategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.sections.chooser.ChooserSectionAdapter
import com.egoriku.ladyhappy.postcreator.presentation.sections.images.ImagesSectionAdapter
import com.egoriku.ladyhappy.postcreator.presentation.sections.input.InputSectionAdapter
import com.egoriku.ladyhappy.postcreator.presentation.state.UploadEvents
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlin.properties.Delegates

const val CHOOSER_KEY = "chooserKey"
const val BUNDLE_KEY = "bundleKey"

class PostCreatorFragment : ScopeFragment(R.layout.fragment_post_creator) {

    init {
        loadKoinModules(postModule)
    }

    private val router: IRouter by inject()

    private val viewModel by viewModel<PostViewModel>()

    private val binding by viewBinding(FragmentPostCreatorBinding::bind)

    private val concatAdapter = ConcatAdapter()
    private var inputSectionAdapter: InputSectionAdapter by Delegates.notNull()
    private var imagesSectionAdapter: ImagesSectionAdapter by Delegates.notNull()
    private var chooserSectionAdapter: ChooserSectionAdapter by Delegates.notNull()

    private val imageChooserContract = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        viewModel.processImageResult(it)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.setFragmentResultListenerWrapper(
                requestKey = CHOOSER_KEY,
                lifecycleOwner = viewLifecycleOwner
        ) { _, result ->
            when (val dialogResult = result.getParcelable<DialogResult>(BUNDLE_KEY)) {
                is DialogResult.Category -> viewModel.setCategory(dialogResult.category)
                is DialogResult.SubCategory -> viewModel.setSubCategory(dialogResult.subCategory)
                is DialogResult.Color -> viewModel.setColor(dialogResult.colorId)
            }
        }

        binding.initView()
        chooserSectionAdapter = ChooserSectionAdapter(
                chooserItemClick = {
                    processChooserItemClick(it)
                },
                resetItemClick = {
                    processResetItemClick(it)
                }
        )

        inputSectionAdapter = InputSectionAdapter(
                onTextChanges = { viewModel.setTitle(it) },
                onRemoveText = { viewModel.setTitle(EMPTY) }
        )

        imagesSectionAdapter = ImagesSectionAdapter(
                onChooseImage = { imageChooserContract.launch("image/*") },
                onRemoveImage = { viewModel.removeAttachedImage(it) }
        )

        concatAdapter.addAdapter(inputSectionAdapter)
        concatAdapter.addAdapter(imagesSectionAdapter)
        concatAdapter.addAdapter(chooserSectionAdapter)

        lifecycleScope.launchWhenResumed {
            viewModel.screenState.collect { state ->
                chooserSectionAdapter.submitList(state.chooserState)
                imagesSectionAdapter.submitList(listOf(state.imagesSection))
                inputSectionAdapter.currentText = state.title
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.publishButtonAvailability.collect {
                binding.postPublishButton.isEnabled = it
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uploadEvents.collect {
                when (it) {
                    is UploadEvents.Error -> {
                        binding.parentProgress.gone()
                        binding.contentLoadingProgressBar.hide()
                        toast("Произошла ошибка во время публикации")
                    }
                    is UploadEvents.InProgress -> {
                        binding.parentProgress.visible()
                        binding.contentLoadingProgressBar.show()
                    }
                    is UploadEvents.Success -> {
                        binding.contentLoadingProgressBar.hide()
                        toast("Запись успешно опубликована")
                        router.back()
                    }
                }
            }
        }
    }

    private fun processChooserItemClick(chooserState: ChooserType) = when (chooserState) {
        is ChooserType.Category -> {
            CategoriesDialog.newInstance(PredefinedData.getCategoriesNames())
                    .show(childFragmentManager, null)
        }
        is ChooserType.SubCategory -> {
            SubCategoriesDialog.newInstance(PredefinedData.getSubCategoriesNames(chooserState.categoryId))
                    .show(childFragmentManager, null)
        }
        is ChooserType.Color -> {
            ColorDialog().show(childFragmentManager, null)
        }
    }

    private fun processResetItemClick(chooserType: ChooserType) {
        viewModel.resetByChooserType(chooserType)
    }

    private fun FragmentPostCreatorBinding.initView() {
        postToolbar.setNavigationOnClickListener {
            router.back()
        }

        postRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
        }

        postPublishButton.setOnClickListener {
            viewModel.publishPost()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(postModule)
    }
}