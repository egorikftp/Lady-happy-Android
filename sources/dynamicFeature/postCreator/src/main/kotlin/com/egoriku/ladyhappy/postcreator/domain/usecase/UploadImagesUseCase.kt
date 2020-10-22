package com.egoriku.ladyhappy.postcreator.domain.usecase

import com.egoriku.ladyhappy.postcreator.data.local.CompressImageRepository
import com.egoriku.ladyhappy.postcreator.data.local.CompressImageRepository.SIZE
import com.egoriku.ladyhappy.postcreator.data.local.CreateFileRepository
import com.egoriku.ladyhappy.postcreator.data.remote.UploadPostImageRepository
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.model.UploadedImageUrl
import com.egoriku.ladyhappy.network.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class UploadImagesUseCase(
        private val createFileRepository: CreateFileRepository,
        private val compressImageRepository: CompressImageRepository,
        private val uploadPostImageRepository: UploadPostImageRepository
) : UseCase<List<ImageItem>, List<UploadedImageUrl>>(Dispatchers.IO) {

    override suspend fun execute(parameters: List<ImageItem>): List<UploadedImageUrl> {
        return coroutineScope {
            parameters.map {
                val imageFile = createFileRepository.fileFromUri(it.uri)

                val previewImageUrl = async {
                    val fileSmall = compressImageRepository.resizeImage(file = imageFile, size = SIZE.PREVIEW)

                    uploadPostImageRepository.upload(
                            fileName = fileSmall.name.addPrefixInImageName("preview"),
                            bytes = compressImageRepository.convertFileToByteArray(fileSmall)
                    )
                }

                val largeImageUrl = async {
                    val fileSmall = compressImageRepository.resizeImage(file = imageFile, size = SIZE.LARGE)

                    uploadPostImageRepository.upload(
                            fileName = fileSmall.name.addPrefixInImageName("large"),
                            bytes = compressImageRepository.convertFileToByteArray(fileSmall)
                    )
                }

                UploadedImageUrl(
                        previewSize = previewImageUrl.await(),
                        largeSize = largeImageUrl.await()
                )
            }
        }
    }

    private fun String.addPrefixInImageName(prefix: String): String {
        val withoutSuffix = removeSuffix("jpg")

        return "${withoutSuffix}_$prefix.jpg"
    }
}