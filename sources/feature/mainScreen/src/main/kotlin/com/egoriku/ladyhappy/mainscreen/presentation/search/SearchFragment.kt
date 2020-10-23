package com.egoriku.ladyhappy.mainscreen.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.mainscreen.R
import com.google.android.material.textfield.TextInputEditText

private const val SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA"

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchQuery: String by extraNotNull(SEARCH_QUERY_EXTRA)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextInputEditText>(R.id.searchInput).setText(searchQuery)
    }

    companion object {
        fun newInstance(searchQuery: String?) = SearchFragment().apply {
            arguments = bundleOf(SEARCH_QUERY_EXTRA to searchQuery)
        }
    }
}