package com.egoriku.core.models

interface ILandingModel {

    val aboutInfo: String

    val quote: String

    val teamMembers: List<ITeamMemberModel>
}

interface ITeamMemberModel {

    val profileImage: String

    val name: String

    val skills: String

    val socialLinks: List<ISocialModel>
}

interface ISocialModel {

    val socialUrl: String

    val type: String
}