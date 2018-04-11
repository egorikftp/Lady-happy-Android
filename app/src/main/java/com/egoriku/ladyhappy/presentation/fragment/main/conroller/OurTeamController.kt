package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.second
import com.egoriku.ladyhappy.data.entities.main.OurTeamEntity
import kotlinx.android.synthetic.main.adapter_item_our_team.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class OurTeamController(val onSocialItemClick: (url: String) -> Unit) : BindableItemController<OurTeamEntity, OurTeamController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: OurTeamEntity) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<OurTeamEntity>(parent, R.layout.adapter_item_our_team) {

        private val firstPersonImage = itemView.personImage
        private val secondPersonImage = itemView.personImage2

        private val firstPersonName = itemView.name
        private val secondPersonName = itemView.name2

        private val firstPersonSkills = itemView.skills
        private val secondPersonSkills = itemView.skills2

        private val firstSocialView = itemView.socialView
        private val secondSocialView = itemView.socialView2

        override fun bind(data: OurTeamEntity) {
            data.ourTeam.first().also {
                firstPersonName.text = it.name
                firstPersonSkills.text = it.skills

                firstSocialView.apply {
                    setSocialModel(it.socialModel)
                    setOnClickListener { showView() }
                    setOnSocialIconClickListener { onSocialItemClick(it) }
                }

                Glide.with(itemView.context)
                        .load(it.personImageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(firstPersonImage)
            }

            data.ourTeam.second().also {
                secondPersonName.text = it.name
                secondPersonSkills.text = it.skills

                secondSocialView.apply {
                    setSocialModel(it.socialModel)
                    setOnClickListener { showView() }
                    secondSocialView.setOnSocialIconClickListener { onSocialItemClick(it) }
                }

                Glide.with(itemView.context)
                        .load(it.personImageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(secondPersonImage)
            }
        }
    }
}
