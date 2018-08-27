package com.egoriku.ladyhappy.data.entities.main

import android.support.annotation.DrawableRes
import android.support.annotation.Keep
import com.egoriku.featureactivitymain.common.Constants
import com.google.firebase.firestore.PropertyName

@Keep
data class OurTeamEntity(
        @get:PropertyName("ourTeam")
        @set:PropertyName("ourTeam")
        var ourTeam: List<TeamMember> = mutableListOf()
)

@Keep
data class SocialModel(val url: String, @DrawableRes val imageResId: Int)

@Keep
data class TeamMember(
        @get:PropertyName("imageUrl")
        @set:PropertyName("imageUrl")
        var personImageUrl: String = Constants.EMPTY,

        @get:PropertyName("name")
        @set:PropertyName("name")
        var name: String = Constants.EMPTY,

        @get:PropertyName("skills")
        @set:PropertyName("skills")
        var skills: String = Constants.EMPTY,

        @get:PropertyName("social")
        @set:PropertyName("social")
        var socialModel: List<SocialModel> = emptyList()
)