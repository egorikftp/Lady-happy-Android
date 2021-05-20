package com.egoriku.ladyhappy.auth.koin

import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.auth.permission.IUserPermission
import com.egoriku.ladyhappy.auth.permission.UserPermission
import com.egoriku.ladyhappy.auth.permission.datasource.IUserDataSource
import com.egoriku.ladyhappy.auth.permission.datasource.UserDataSource
import com.egoriku.ladyhappy.auth.permission.repository.IUserPermissionsRepository
import com.egoriku.ladyhappy.auth.permission.repository.UserPermissionsRepository
import org.koin.dsl.module

val authModule = module {

    single { Authentication() }
    single<IUserPermission> {
        UserPermission(
            authentication = get(),
            userPermissionsRepository = get()
        )
    }

    factory<IUserDataSource> {
        UserDataSource(firebase = get())
    }

    factory<IUserPermissionsRepository> {
        UserPermissionsRepository(userDataSource = get())
    }
}