package com.egoriku.ladyhappy.postcreator.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.IRouter
import com.egoriku.extensions.setFragmentResultListenerWrapper
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.FragmentPostCreatorBinding
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.koin.postModule
import com.egoriku.ladyhappy.postcreator.presentation.PostViewModel
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.CategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.ColorDialog
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.SubCategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.model.Chooser
import com.egoriku.ladyhappy.postcreator.presentation.model.Type
import com.egoriku.ladyhappy.postcreator.presentation.section.ChooserSectionAdapter
import com.egoriku.ladyhappy.postcreator.presentation.section.ImagesSectionAdapter
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlin.properties.Delegates

const val CHOOSER_KEY = "chooserKey"
const val BUNDLE_KEY = "bundleKey"

class PostCreatorFragment : Fragment(R.layout.fragment_post_creator) {

    init {
        loadKoinModules(postModule)
    }

    private val binding by viewBinding(FragmentPostCreatorBinding::bind)

    private val router: IRouter by inject()

    private val viewModel: PostViewModel by lifecycleScope.viewModel(this)

    private val concatAdapter = ConcatAdapter()
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
                is DialogResult.SubCategory -> viewModel.updateSubCategory(dialogResult.subCategory)
                is DialogResult.Color -> {}
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

        imagesSectionAdapter = ImagesSectionAdapter(
                chooseImage = {
                    imageChooserContract.launch("image/*")
                },
                removeImage = {
                    viewModel.removeAttachedImage(it)
                }
        )

        concatAdapter.addAdapter(imagesSectionAdapter)
        concatAdapter.addAdapter(chooserSectionAdapter)

        viewModel.screenState.observe(viewLifecycleOwner) {
            chooserSectionAdapter.submitList(it.chooser)
            imagesSectionAdapter.submitList(listOf(it.imagesSection))
        }
    }

    private fun processChooserItemClick(chooser: Chooser) = when (chooser.type) {
        Type.CATEGORY -> {
            CategoriesDialog.newInstance(PredefinedData.getCategoriesNames()).show(childFragmentManager, null)
        }
        Type.SUBCATEGORY -> {
            SubCategoriesDialog.newInstance(PredefinedData.getSubCategoriesNames(chooser.optionalData))
                    .show(childFragmentManager, null)
        }
        Type.COLOR -> {
            ColorDialog().show(childFragmentManager, null)
        }
    }

    private fun processResetItemClick(it: Chooser.Selected) = when (it.type) {
        Type.CATEGORY -> {
            viewModel.resetByType(Type.CATEGORY)
            viewModel.resetSubCategory()
        }
        Type.SUBCATEGORY -> viewModel.resetSubCategory()
        Type.COLOR -> {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(postModule)
    }

    private fun FragmentPostCreatorBinding.initView() {
        postToolbar.setNavigationOnClickListener {
            router.back()
        }

        postRecycler.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = concatAdapter
        }

        postPublishButton.setOnClickListener {
            viewModel.publishPost()
        }
    }
}