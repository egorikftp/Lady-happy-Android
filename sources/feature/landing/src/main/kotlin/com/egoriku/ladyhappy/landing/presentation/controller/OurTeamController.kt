package com.egoriku.ladyhappy.landing.presentation.controller

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import coil.load
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.landing.R
import com.egoriku.ladyhappy.landing.common.parallax.ParallaxScrollListener
import com.egoriku.ladyhappy.landing.common.parallax.ParallaxScrollStateListener
import com.egoriku.ladyhappy.landing.databinding.AdapterItemOurTeamBinding
import com.egoriku.ladyhappy.landing.domain.model.TeamMemberModel
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class OurTeamController(
        private val parallaxScrollListener: ParallaxScrollListener?,
        private val onSocialItemClick: (url: String) -> Unit
) : BindableItemController<TeamMemberModel, OurTeamController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemOurTeamBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: TeamMemberModel) = data.hashCode().toString()

    inner class Holder(
            private val itemBinding: AdapterItemOurTeamBinding
    ) : BindableViewHolder<TeamMemberModel>(itemBinding.root),
            ParallaxScrollStateListener {

        init {
            parallaxScrollListener?.addListener(this@Holder)
        }

        private val placeholderDrawable = ColorDrawable(itemView.colorFromAttr(R.attr.colorPlaceholder))

        private val socialViewContainer = itemBinding.socialView.apply {
            setOnClickListener { showView() }
            setOnSocialIconClickListener { onSocialItemClick(it) }
        }

        override fun bind(model: TeamMemberModel) {
            itemBinding.ourTeamName.text = model.name
            itemBinding.ourTeamBio.text = model.skills

            socialViewContainer.setSocialModel(model.socialLinks)

            itemBinding.ourTeamPersonImage.load(uri = model.profileImage) {
                placeholder(placeholderDrawable)
            }
        }

        override fun onScroll() = socialViewContainer.hideView()

        override fun onAttach() = Unit

        override fun onDetach() = Unit
    }
}
