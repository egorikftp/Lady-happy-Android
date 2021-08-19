package com.egoriku.ladyhappy.auth.permission

import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.auth.permission.repository.IUserPermissionsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class UserPermission(
    private val authentication: Authentication,
    private val userPermissionsRepository: IUserPermissionsRepository
) : IUserPermission {

    private val serviceJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + serviceJob)

    private val permissions = mutableListOf<Permission>()

    init {
        scope.launch {
            authentication.userLoginState.collect {
                when (it) {
                    is UserLoginState.Anon -> {
                        permissions.clear()
                        permissions.add(Permission.Anon)
                    }
                    is UserLoginState.LoggedIn -> {
                        permissions.clear()
                        permissions.addAll(
                            userPermissionsRepository.getPermissionsBy(userId = it.userId)
                        )
                    }
                }
            }
        }
    }

    override val isAdminMode: Boolean
        get() = check(Permission.Admin)

    override val isAnonMode: Boolean
        get() = check(Permission.Anon)

    override val isDemoMode: Boolean
        get() = check(Permission.DemoMode)

    override val isGeneralUser: Boolean
        get() = check(Permission.User)

    private fun check(permission: Permission): Boolean = permissions.contains(permission)
}

interface IUserPermission {
    val isAdminMode: Boolean
    val isAnonMode: Boolean
    val isGeneralUser: Boolean
    val isDemoMode: Boolean
}