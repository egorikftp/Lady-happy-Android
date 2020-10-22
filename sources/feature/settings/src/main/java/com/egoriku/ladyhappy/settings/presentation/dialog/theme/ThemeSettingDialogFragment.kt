package com.egoriku.ladyhappy.settings.presentation.dialog.theme

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.egoriku.ladyhappy.core.sharedmodel.Theme
import com.egoriku.ladyhappy.settings.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates

class ThemeSettingDialogFragment : AppCompatDialogFragment() {

    private val viewModel: ThemeViewModel by lifecycleScope.viewModel(this)

    private var listAdapter: ArrayAdapter<ThemeHolder> by Delegates.notNull()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listAdapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_single_choice)

        return MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.settings_theme_title)
                .setSingleChoiceItems(listAdapter, 0) { dialog, position ->
                    listAdapter.getItem(position)?.theme?.let {
                        viewModel.setTheme(it)
                    }
                    dialog.dismiss()
                }
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.availableThemes.observe(this) { themes ->
            listAdapter.clear()
            listAdapter.addAll(themes.map { theme -> ThemeHolder(theme, getTitleForTheme(theme)) })

            updateSelectedItem(viewModel.theme.value)
        }

        viewModel.theme.observe(this) {
            updateSelectedItem(it)
        }
    }

    private fun updateSelectedItem(selected: Theme?) {
        val selectedPosition = (0 until listAdapter.count).indexOfFirst { index ->
            listAdapter.getItem(index)?.theme == selected
        }
        (dialog as AlertDialog).listView.setItemChecked(selectedPosition, true)
    }

    private fun getTitleForTheme(theme: Theme) = when (theme) {
        Theme.LIGHT -> getString(R.string.settings_theme_light)
        Theme.DARK -> getString(R.string.settings_theme_dark)
        Theme.SYSTEM -> getString(R.string.settings_theme_system)
        Theme.BATTERY_SAVER -> getString(R.string.settings_theme_battery)
    }

    private data class ThemeHolder(val theme: Theme, val title: String) {

        override fun toString(): String = title
    }
}