package com.egoriku.landing.presentation.controller

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.egoriku.extensions.drawableCompat
import com.egoriku.extensions.inflater
import com.egoriku.landing.R
import com.egoriku.landing.common.parallax.ParallaxScrollListener
import com.egoriku.landing.common.parallax.ParallaxScrollStateListener
import com.egoriku.landing.databinding.AdapterItemOurTeamBinding
import com.egoriku.landing.domain.model.TeamMemberModel
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

        private val requestOptions = RequestOptions().placeholder(itemView.drawableCompat(R.color.RealBlack30))

        private val socialViewContainer = itemBinding.socialView.apply {
            setOnClickListener { showView() }
            setOnSocialIconClickListener { onSocialItemClick(it) }
        }

        override fun bind(model: TeamMemberModel) {
            itemBinding.ourTeamName.text = model.name
            itemBinding.ourTeamBio.text = model.skills

            socialViewContainer.setSocialModel(model.socialLinks)

            Glide.with(itemView.context)
                    .load(model.profileImage)
                    .apply(requestOptions)
                    .into(itemBinding.ourTeamPersonImage)
        }

        override fun onScroll() = socialViewContainer.hideView()

        override fun onAttach() = Unit

        override fun onDetach() = Unit
    }
}
