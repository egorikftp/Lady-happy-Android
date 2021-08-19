package com.egoriku.ladyhappy.auth.permission.repository

import com.egoriku.ladyhappy.auth.permission.Permission
import com.egoriku.ladyhappy.auth.permission.datasource.IUserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class UserPermissionsRepository(
    private val userDataSource: IUserDataSource
) : IUserPermissionsRepository {

    private val transformation: (String) -> Permission? = { permission: String ->
        when (permission) {
            Permission.Admin.name -> Permission.Admin
            Permission.User.name -> Permission.User
            Permission.DemoMode.name -> Permission.DemoMode
            else -> null
        }
    }

    override suspend fun getPermissionsBy(userId: String) = withContext(Dispatchers.IO) {
        runCatching {
            val entity = userDataSource.fetch(userId = userId) // Legacy user without necessary data
                ?: return@withContext emptyList()

            when (entity.userId) {
                userId -> entity.permissions.mapNotNull(transformation)
                else -> emptyList()
            }
        }.getOrElse {
            emptyList()
        }
    }
}

interface IUserPermissionsRepository {

    suspend fun getPermissionsBy(userId: String): List<Permission>
}