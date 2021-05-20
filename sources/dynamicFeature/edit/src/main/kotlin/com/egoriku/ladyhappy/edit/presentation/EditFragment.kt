package com.egoriku.ladyhappy.edit.presentation

import android.content.Context
import android.os.Bundle
import android.text.InputType.*
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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
import com.google.android.play.core.splitcompat.SplitCompat
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputEditText
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf
import com.egoriku.ladyhappy.localization.R as R_localization

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.initViews()

        repeatingJobOnStarted {
            viewModel.uiState.collect {
                binding.handleState(it)
            }
        }

        repeatingJobOnStarted {
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
                    titleResId = R_localization.string.edit_dialog_header_edit_name,
                    predefinedValue = titleView.text.toString(),
                    inputType = TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_MULTI_LINE
            ) {
                viewModel.setEvent(Event.UpdateTitle(title = it))
            }
        }

        descriptionView.setOnClickListener {
            showEditBottomSheetDialog(
                    titleResId = R_localization.string.edit_dialog_header_edit_description,
                    predefinedValue = descriptionView.text.toString(),
                    inputType = TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_MULTI_LINE
            ) {
                viewModel.setEvent(Event.UpdateDescription(description = it))
            }
        }

        countView.setOnClickListener {
            showEditBottomSheetDialog(
                    titleResId = R_localization.string.edit_dialog_header_edit_count,
                    predefinedValue = countView.text.toString(),
                    inputType = TYPE_CLASS_NUMBER or TYPE_TEXT_FLAG_MULTI_LINE
            ) {
                viewModel.setEvent(Event.UpdateCount(count = it.toInt()))
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
                technicalInfo.visible()

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
                countView.text = model.publishedCount.toString()
                lastEditTimeView.text = model.lastEditTime
                documentReferenceView.text = model.documentReference
            }
        }
    }

    @Suppress("ForbiddenComment")
    private fun showEditBottomSheetDialog(
            titleResId: Int,
            predefinedValue: String,
            inputType: Int,
            onNewValue: (String) -> Unit
    ) {
        InputSheet().show(requireContext()) {
            title(titleResId)
            with(
                    InputEditText {
                        required()
                        inputType(inputType)
                        defaultValue(predefinedValue)
                    }
            )
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