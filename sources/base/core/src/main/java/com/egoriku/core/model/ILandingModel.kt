package com.egoriku.core.model

interface ILandingModel {

    val aboutInfo: String

    val quotes: List<IQuotesModel>

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

interface IQuotesModel {

    val quote: String

    val author: String
}