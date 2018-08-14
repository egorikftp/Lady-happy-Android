package com.egoriku.core.models

interface ILandingModel {

    fun aboutInfo(): String

    fun quote(): String

    //fun teamMembers(): List<ITeamMember>
}

interface ITeamMember {

    fun profileImage(): String

    fun name(): String

    fun skills(): String

}