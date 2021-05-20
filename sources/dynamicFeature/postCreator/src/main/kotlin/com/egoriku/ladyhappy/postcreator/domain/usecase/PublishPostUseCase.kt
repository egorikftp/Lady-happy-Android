package com.egoriku.ladyhappy.postcreator.domain.usecase

import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.usecase.UseCase
import com.egoriku.ladyhappy.postcreator.data.entity.UploadEntity
import com.egoriku.ladyhappy.postcreator.data.remote.PublishPostRepository
import kotlinx.coroutines.Dispatchers

class PublishPostUseCase(
    private val publishPostRepository: PublishPostRepository,
) : UseCase<UploadEntity, ResultOf<Boolean>>(Dispatchers.IO) {

    override suspend fun execute(parameters: UploadEntity): ResultOf<Boolean> =
        publishPostRepository.publish(uploadEntity = parameters)
}