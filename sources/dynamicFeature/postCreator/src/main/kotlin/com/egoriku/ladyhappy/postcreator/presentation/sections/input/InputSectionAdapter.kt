package com.egoriku.ladyhappy.postcreator.presentation.sections.input

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.extensions.listeners.SimpleTextWatcher
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemInputBinding

class InputSectionAdapter(
    private val onTextChanges: (title: String) -> Unit,
) : RecyclerView.Adapter<InputSectionAdapter.VH>() {

    var currentText: String = EMPTY

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = VH(AdapterItemInputBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(currentText)

    override fun getItemCount(): Int = 1

    inner class VH(
        private val binding: AdapterItemInputBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val textWatcher = object : SimpleTextWatcher {
            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                currentText = charSequence.toString()
                onTextChanges(charSequence.toString())
            }
        }

        init {
            with(binding) {
                postTitleInput.editText?.addTextChangedListener(textWatcher)
                postTitleInput.setEndIconOnClickListener {
                    postTitleInput.editText?.setText(EMPTY)
                }
            }
        }

        fun bind(text: String) {
            with(binding) {
                val editText = postTitleInput.editText ?: return

                editText.removeTextChangedListener(textWatcher)
                editText.setText(text)
                editText.addTextChangedListener(textWatcher)
            }
        }
    }
}