package com.egoriku.ladyhappy.edit.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.core.sharedmodel.key.EDIT_BUNDLE_RESULT_KEY
import com.egoriku.ladyhappy.core.sharedmodel.key.EDIT_REQUEST_KEY
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_DOCUMENT_REFERENCE
import com.egoriku.ladyhappy.edit.R
import com.egoriku.ladyhappy.edit.databinding.FragmentEditSubcategoryBinding
import com.egoriku.ladyhappy.edit.koin.editModule
import com.egoriku.ladyhappy.extensions.*
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputEditText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class EditFragment : ScopeFragment(R.layout.fragment_edit_subcategory) {

    init {
        loadKoinModules(editModule)
    }

    private val router: IRouter by inject()

    private val binding by viewBinding(FragmentEditSubcategoryBinding::bind)

    private val documentReferenceExtra by extraNotNull<String>(KEY_DOCUMENT_REFERENCE)

    private val viewModel by viewModel<EditSubCategoryViewModel> {
        parametersOf(documentReferenceExtra)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.initViews()

        lifecycleScope.launch {
            viewModel.uiState.collect {
                binding.handleState(it)
            }
        }

        lifecycleScope.launch {
            viewModel.effect.collect {
                when (it) {
                    is Effect.Exit -> {
                        setFragmentResult(EDIT_REQUEST_KEY, bundleOf(EDIT_BUNDLE_RESULT_KEY to it.categoryId))
                        router.back()
                    }
                    is Effect.ShowToast -> toast(it.message)
                }
            }
        }
    }

    private fun FragmentEditSubcategoryBinding.initViews() {
        editToolbar.setNavigationOnClickListener { router.back() }

        popularSwitchView.setOnCheckedChangeListener { isChecked ->
            viewModel.setEvent(Event.UpdatePopular(isPopular = isChecked))
        }

        titleView.setOnClickListener {
            showEditBottomSheetDialog(
                    title = "Edit title",
                    predefinedInput = titleView.text.toString()
            ) {
                viewModel.setEvent(Event.UpdateTitle(title = it))
            }
        }

        descriptionView.setOnClickListener {
            showEditBottomSheetDialog(
                    title = "Edit description",
                    predefinedInput = descriptionView.text.toString()
            ) {
                viewModel.setEvent(Event.UpdateDescription(description = it))
            }
        }

        updateButton.setOnClickListener {
            viewModel.setEvent(Event.SaveEditChanges)
        }
    }

    private fun FragmentEditSubcategoryBinding.handleState(state: State) {
        when (val editState = state.editState) {
            is EditState.Initial, EditState.Loading -> {
                contentLoadingProgressBar.visible()
                errorText.gone()
            }
            is EditState.Error -> {
                contentLoadingProgressBar.gone()
                errorText.visible()
            }

            is EditState.Success -> {
                contentLoadingProgressBar.gone()
                errorText.gone()
                popularSwitchView.visible()

                val model = editState.subCategoryModel

                mozaikLayout.visible()
                mozaikLayout.onViewReady = { view, url ->
                    Glide.with(view)
                            .load(url)
                            .into(view)
                }
                mozaikLayout.setItems(model.images)
                popularSwitchView.isChecked = model.isPopular
                titleView.text = model.subCategoryName
                descriptionView.text = model.description
                documentReferenceView.text = model.documentReference
            }
        }
    }

    private fun showEditBottomSheetDialog(
            title: String,
            predefinedInput: String,
            onNewValue: (String) -> Unit
    ) {
        InputSheet().show(requireContext()) {
            title(title)
            with(input = InputEditText {
                defaultValue(predefinedInput)
            })
            onPositive { result ->
                onNewValue(result.getStringOrThrow("0"))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(editModule)
    }
}