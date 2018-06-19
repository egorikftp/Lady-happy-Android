package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.data.entities.main.TeamMember
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_our_team.*
import kotlinx.android.synthetic.main.adapter_item_our_team.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class OurTeamController(val onSocialItemClick: (url: String) -> Unit) : BindableItemController<TeamMember, OurTeamController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: TeamMember) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<TeamMember>(parent, R.layout.adapter_item_our_team), LayoutContainer {
        override val containerView: View
            get() = itemView

        private val firstSocialView = itemView.socialView.apply {
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
