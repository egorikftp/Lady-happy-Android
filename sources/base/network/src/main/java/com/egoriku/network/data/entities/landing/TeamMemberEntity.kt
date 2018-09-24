package com.egoriku.network.data.entities.landing

import android.support.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
class TeamMemberEntity {

    @PropertyName("imageUrl")
    @JvmField
    val personImageUrl: String? = null

    @PropertyName("name")
    @JvmField
    val name: String? = null

    @PropertyName("skills")
    @JvmField
    val skills: String? = null

    @PropertyName("socialLinks")
    @JvmField
    val socialLinks: List<SocialEntity>? = null
}