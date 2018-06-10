package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.data.entities.main.TeamMember
import kotlinx.android.synthetic.main.adapter_item_our_team.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class OurTeamController(val onSocialItemClick: (url: String) -> Unit) : BindableItemController<TeamMember, OurTeamController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: TeamMember) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<TeamMember>(parent, R.layout.adapter_item_our_team) {

        private val firstPersonImage = itemView.personImage

        private val firstPersonName = itemView.name

        private val firstPersonSkills = itemView.skills

        private val firstSocialView = itemView.socialView.apply {
            setOnClickListener { showView() }
            setOnSocialIconClickListener { onSocialItemClick(it) }
        }

        override fun bind(data: TeamMember) {
            with(data) {
                firstPersonName.text = name
                firstPersonSkills.text = skills
                firstSocialView.setSocialModel(socialModel)

                Glide.with(itemView.context)
                        .load(personImageUrl)
                        .into(firstPersonImage)
            }
        }
    }
}
