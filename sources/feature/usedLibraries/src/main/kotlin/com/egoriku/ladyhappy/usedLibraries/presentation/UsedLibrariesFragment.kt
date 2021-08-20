package com.egoriku.ladyhappy.usedLibraries.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.extensions.browseUrl
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.ladyhappy.usedLibraries.R
import com.egoriku.ladyhappy.usedLibraries.databinding.FragmentUsedLibrariesBinding
import com.egoriku.ladyhappy.usedLibraries.presentation.adapter.LibrariesListAdapter
import com.egoriku.ladyhappy.usedLibraries.presentation.screen.LicenseFragmentScreen
import com.egoriku.ladyhappy.usedLibraries.presentation.state.ScreenState
import com.egoriku.ladyhappy.usedLibraries.presentation.viewmodel.UsedLibrariesViewModel
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class UsedLibrariesFragment : ScopeFragment(R.layout.fragment_used_libraries) {

    private val binding by viewBinding(FragmentUsedLibrariesBinding::bind)

    private val viewModel by viewModel<UsedLibrariesViewModel>()

    private val router: IRouter by inject()

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
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
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