package com.egoriku.ladyhappy.auth.permission

import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.auth.permission.repository.IUserPermissionsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class UserPermission(
        private val authentication: Authentication,
        private val userPermissionsRepository: IUserPermissionsRepository
) : IUserPermission {

    private val permissions = mutableListOf<Permission>()

    init {
        GlobalScope.launch {
            authentication.userLoginState.collect {
                when (it) {
                    is UserLoginState.Anon -> permissions.clear()
                    is UserLoginState.LoggedIn -> {
                        val newPermissions = userPermissionsRepository.getPermissionsBy(userId = it.userId)

                        permissions.addAll(newPermissions)
                    }
                }
            }
        }
    }

    override val isAbleToCreatePosts: Boolean
        get() = check(Permission.Admin)

    override val isAbleToEditPosts: Boolean
        get() = check(Permission.Admin)

    private fun check(permission: Permission): Boolean = permissions.contains(permission)
}

interface IUserPermission {
    val isAbleToCreatePosts: Boolean
    val isAbleToEditPosts: Boolean
}