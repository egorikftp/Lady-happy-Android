package com.egoriku.ladyhappy.auth.permission.datasource

import com.egoriku.ladyhappy.auth.permission.entity.UserEntity
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.key.CollectionPath.USERS
import com.egoriku.ladyhappy.network.firestore.awaitGet

internal class UserDataSource(
        private val firebase: IFirebase
) : IUserDataSource {

    override suspend fun fetch(userId: String) = firebase.firebaseFirestore
            .collection(USERS)
            .document(userId)
            .awaitGet<UserEntity>()
}

interface IUserDataSource {

    suspend fun fetch(userId: String): UserEntity?
}