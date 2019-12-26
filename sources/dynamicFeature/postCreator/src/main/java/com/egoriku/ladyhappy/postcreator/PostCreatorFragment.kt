package com.egoriku.ladyhappy.postcreator

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitcompat.SplitCompat

class PostCreatorFragment : Fragment(R.layout.fragment_post_creator) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(context)
    }
}