package com.egoriku.ladyhappy.postcreator.presentation.fragment

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.extensions.*
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.FragmentPostCreatorBinding
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.koin.postModule
import com.egoriku.ladyhappy.postcreator.presentation.PostViewModel
import com.egoriku.ladyhappy.postcreator.presentation.ScreenState
import com.egoriku.ladyhappy.postcreator.presentation.adapter.AddImageAdapter
import com.egoriku.ladyhappy.postcreator.presentation.adapter.ImagesAdapter
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.CategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.SubCategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.listener.DialogValueChangeListener
import com.egoriku.ui.decorator.VerticalMarginItemDecoration
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlin.properties.Delegates
import com.egoriku.ladyhappy.localization.R as R_localization
import com.egoriku.ui.R as R_ui

class PostCreatorFragment : Fragment(R.layout.fragment_post_creator),
        DialogValueChangeListener {

    init {
        loadKoinModules(postModule)
    }

    private val binding: FragmentPostCreatorBinding by viewBinding()

    private val router: IRouter by inject()

    private val viewModel: PostViewModel by lifecycleScope.viewModel(this)

    private val mergeAdapter = ConcatAdapter()
    private var imagesAdapter: ImagesAdapter by Delegates.notNull()

    private val imageChooserContract = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        viewModel.processImageResult(it)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind()

        mergeAdapter.addAdapter(AddImageAdapter {
            imageChooserContract.launch("image/*")
        })

        imagesAdapter = ImagesAdapter {
            viewModel.removeAttachedImage(it)
        }

        mergeAdapter.addAdapter(imagesAdapter)

        viewModel.images.observe(viewLifecycleOwner) {
            imagesAdapter.submitList(it)

            updateImagesCount(it)
        }

        viewModel.screenState.observe(viewLifecycleOwner) {
            binding.processViewState(it)
        }
    }

    private fun updateImagesCount(list: List<ImageItem>) {
        val size = list.size

        binding.apply {
            if (size > 10) {
                postImagesCount.setTextColor(colorFromAttr(R_ui.attr.colorPrimary))
                postImagesCount.setTypeface(null, Typeface.BOLD)

                icon.imageTintList = colorStateListCompat(R_ui.color.RoseTaupe)
            } else {
                postImagesCount.setTextColor(colorFromAttr(R_ui.attr.colorOnSurface))
                postImagesCount.setTypeface(null, Typeface.NORMAL)

                icon.imageTintList = colorStateListCompat(R_ui.color.RealBlack)
            }

            postImagesCount.text = String.format(getString(R_localization.string.post_creator_images_count), list.size)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(postModule)
    }

    override fun onValueChanged(dialogResult: DialogResult) {
        when (dialogResult) {
            is DialogResult.Category -> viewModel.updateCategory(dialogResult.category)
            is DialogResult.SubCategory -> viewModel.updateSubCategory(dialogResult.subCategory)
        }
    }

    private fun FragmentPostCreatorBinding.bind() {
        postToolbar.setNavigationOnClickListener {
            router.back()
        }

        postImagesRecycler.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = mergeAdapter
            addItemDecoration(VerticalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.posts_images_margin)))
        }

        chooseCategory.setOnClickListener {
            //TODO make a local router
            CategoriesDialog.newInstance(PredefinedData.getCategoriesNames())
                    .show(childFragmentManager, null)
        }

        chooseCategory.onClearIconClickListener = {
            viewModel.updateCategory(category = null)
        }

        chooseSubCategory.onClearIconClickListener = {
            viewModel.updateCategory(category = null)
            viewModel.updateSubCategory(subCategory = null)
        }

        postPublishButton.setOnClickListener {
            toast("Will upload post")
        }
    }

    private fun FragmentPostCreatorBinding.processViewState(state: ScreenState) {
        when (val category = state.category) {
            null -> {
                chooseCategory.reset()
                chooseSubCategory.gone()
                chooseSubCategory.reset()
            }
            else -> {
                chooseCategory.setPrimary(category.name)

                with(chooseSubCategory) {
                    chooseSubCategory.reset()
                    visible()
                    setOnClickListener {
                        SubCategoriesDialog.newInstance(PredefinedData.getSubCategoriesNames(category.categoryId))
                                .show(childFragmentManager, null)
                    }
                }
            }
        }

        when (val subCategory = state.subCategory) {
            null -> chooseSubCategory.reset()
            else -> chooseSubCategory.setPrimary(subCategory.name)
        }
    }
}