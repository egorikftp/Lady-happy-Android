package com.egoriku.ladyhappy.catalog.categories.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.extensions.toEditable
import com.google.android.material.textfield.TextInputEditText

private const val SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA"

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var searchQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            searchQuery = it.getString(SEARCH_QUERY_EXTRA, EMPTY)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextInputEditText>(R.id.searchInput).text = searchQuery?.toEditable()
    }

    companion object {
        fun newInstance(searchQuery: String?) = SearchFragment().apply {
            arguments = Bundle().apply {
                putString(SEARCH_QUERY_EXTRA, searchQuery)
            }
        }
    }
}