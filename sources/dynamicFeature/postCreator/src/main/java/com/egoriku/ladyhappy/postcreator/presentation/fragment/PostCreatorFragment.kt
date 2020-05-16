package com.egoriku.ladyhappy.postcreator.presentation.fragment

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.colorStateListCompat
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.FragmentPostCreatorBinding
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.koin.postModule
import com.egoriku.ladyhappy.postcreator.presentation.PostViewModel
import com.egoriku.ladyhappy.postcreator.presentation.adapter.AddImageAdapter
import com.egoriku.ladyhappy.postcreator.presentation.adapter.ImagesAdapter
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.CategoriesDialog
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

    private val mergeAdapter = MergeAdapter()
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
            is DialogResult.Categories -> viewModel.updateCategory(dialogResult.category)
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
            CategoriesDialog.newInstance(PredefinedData.categoriesName)
                    .show(childFragmentManager, null)
        }

        chooseSubCategory.setOnClickListener {
            toast("Soon")
        }

        postPublishButton.setOnClickListener {
            toast("Will upload post")
        }
    }
}