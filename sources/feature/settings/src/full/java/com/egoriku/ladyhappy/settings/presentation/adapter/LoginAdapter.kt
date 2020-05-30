package com.egoriku.ladyhappy.settings.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.extensions.drawableCompat
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.AdapterItemLoginBinding
import com.egoriku.ladyhappy.settings.domain.model.Section.Login
import com.egoriku.ladyhappy.settings.presentation.view.State

internal class LoginAdapter(
        private val onItemClick: (loginState: State) -> Unit
) : ListAdapter<Login, LoginAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemLoginBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = R.layout.adapter_item_login

    inner class VH(private val binding: AdapterItemLoginBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Login) {
            when (model.state) {
                is UserLoginState.Anon -> {
                    binding.loginView.apply {
                        state = State.ANON
                        setProfileImage(drawableCompat(R.drawable.ic_profile_stub))
                        onButtonClick {
                            onItemClick(State.ANON)
                        }
                    }
                }

                is UserLoginState.LoggedIn -> {
                    binding.loginView.apply {
                        state = State.LOGGED_IN
                        setProfileImage(drawableCompat(R.drawable.ic_profile_stub))
                        setUserName(if (model.state.name.isEmpty()) model.state.email else model.state.name)
                        onButtonClick {
                            onItemClick(State.LOGGED_IN)
                        }
                    }
                }
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<Login>() {

        override fun areItemsTheSame(oldItem: Login, newItem: Login) = oldItem.state == newItem.state

        override fun areContentsTheSame(oldItem: Login, newItem: Login) = oldItem == newItem
    }
}

