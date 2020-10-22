package com.egoriku.usedLibraries.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.extensions.browseUrl
import com.egoriku.extensions.gone
import com.egoriku.extensions.visible
import com.egoriku.usedLibraries.R
import com.egoriku.usedLibraries.databinding.FragmentUsedLibrariesBinding
import com.egoriku.usedLibraries.presentation.adapter.LibrariesListAdapter
import com.egoriku.usedLibraries.presentation.screen.LicenseFragmentScreen
import com.egoriku.usedLibraries.presentation.state.ScreenState
import com.egoriku.usedLibraries.presentation.viewmodel.UsedLibrariesViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates

class UsedLibrariesFragment : Fragment(R.layout.fragment_used_libraries) {

    private val router: IRouter by inject()

    private val viewModel: UsedLibrariesViewModel by lifecycleScope.viewModel(this)

    private val binding by viewBinding(FragmentUsedLibrariesBinding::bind)

    private var librariesAdapter: LibrariesListAdapter by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        librariesAdapter = LibrariesListAdapter {
            router.addScreenFullscreen(screen = LicenseFragmentScreen(it))
        }

        binding.initViews()

        savedInstanceState?.run {
            binding.motionLayout.progress = getFloat("motion_progress")
        }
    }

    private fun FragmentUsedLibrariesBinding.initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = librariesAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }

        linkToGithub.setOnClickListener {
            browseUrl(getString(R.string.github_link))
        }

        viewModel.licenses.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Success -> {
                    librariesAdapter.submitList(it.licenses)

                    errorView.gone()
                }
                is ScreenState.Error -> errorView.visible()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putFloat("motion_progress", binding.motionLayout.progress)
    }
}