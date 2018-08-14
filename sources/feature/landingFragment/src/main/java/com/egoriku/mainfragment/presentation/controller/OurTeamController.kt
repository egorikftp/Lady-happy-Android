package com.egoriku.mainfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.egoriku.mainfragment.R
import com.egoriku.mainfragment.data.entities.TeamMember
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_our_team.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class OurTeamController(val onSocialItemClick: (url: String) -> Unit) : BindableItemController<TeamMember, OurTeamController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: TeamMember) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<TeamMember>(parent, R.layout.adapter_item_our_team), LayoutContainer {
        override val containerView: View
            get() = itemView

        private val firstSocialView = socialView.apply {
            setOnClickListener { showView() }
            setOnSocialIconClickListener { onSocialItemClick(it) }
        }

        override fun bind(model: TeamMember) {
            ourTeamName.text = model.name
            ourTeamBio.text = model.skills
            firstSocialView.setSocialModel(model.socialModel)

            Glide.with(itemView.context)
                    .load(model.personImageUrl)
                    .into(ourTeamPersonImage)
        }
    }
}
