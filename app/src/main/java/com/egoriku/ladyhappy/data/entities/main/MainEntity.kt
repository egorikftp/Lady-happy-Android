package com.egoriku.ladyhappy.data.entities.main

import android.support.annotation.Keep
import com.egoriku.corelib_kt.Constants
import com.google.firebase.firestore.PropertyName

@Keep
data class OurTeamEntity(
        @get:PropertyName("ourTeam")
        @set:PropertyName("ourTeam")
        var ourTeam: List<TeamMember> = mutableListOf()
)

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
        var skills: String = Constants.EMPTY
)