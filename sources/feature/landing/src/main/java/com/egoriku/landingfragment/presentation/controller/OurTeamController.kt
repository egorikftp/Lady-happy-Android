package com.egoriku.landingfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.egoriku.landingfragment.R
import com.egoriku.landingfragment.common.parallax.IParallaxScrollListener
import com.egoriku.landingfragment.common.parallax.ParallaxScrollListener
import com.egoriku.landingfragment.domain.model.TeamMemberModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_our_team.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class OurTeamController(val parallaxScrollListener: ParallaxScrollListener?, val onSocialItemClick: (url: String) -> Unit) : BindableItemController<TeamMemberModel, OurTeamController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: TeamMemberModel) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<TeamMemberModel>(parent, R.layout.adapter_item_our_team), LayoutContainer, IParallaxScrollListener {
        override val containerView: View
            get() = itemView

        init {
            parallaxScrollListener?.addListener(this@Holder)
        }

        private val requestOptions = RequestOptions().placeholder(ContextCompat.getDrawable(itemView.context, R.color.RealBlack30))

        private val socialViewContainer = socialView.apply {
            setOnClickListener { showView() }
            setOnSocialIconClickListener { onSocialItemClick(it) }
        }

        override fun bind(model: TeamMemberModel) {
            ourTeamName.text = model.name
            ourTeamBio.text = model.skills

            socialViewContainer.setSocialModel(model.socialLinks)

            Glide.with(itemView.context)
                    .load(model.profileImage)
                    .apply(requestOptions)
                    .into(ourTeamPersonImage)
        }

        override fun onScroll() = socialViewContainer.hideView()

        override fun onAttach() = Unit

        override fun onDetach() = Unit
    }
}
