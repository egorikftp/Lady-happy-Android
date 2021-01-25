package com.egoriku.ladyhappy.postcreator.domain.usecase

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import com.egoriku.ladyhappy.network.usecase.UseCase
import com.egoriku.ladyhappy.postcreator.data.entity.UploadedImageBySize
import com.egoriku.ladyhappy.postcreator.data.entity.UploadedImageEntity
import com.egoriku.ladyhappy.postcreator.data.local.CompressImageRepository
import com.egoriku.ladyhappy.postcreator.data.local.CompressImageRepository.SIZE
import com.egoriku.ladyhappy.postcreator.data.local.CreateFileRepository
import com.egoriku.ladyhappy.postcreator.data.remote.UploadImageRepository
import com.egoriku.ladyhappy.postcreator.domain.model.image.UploadImagesParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.File

class UploadImagesUseCase(
        private val createFileRepository: CreateFileRepository,
        private val compressImageRepository: CompressImageRepository,
        private val uploadImageRepository: UploadImageRepository,
) : UseCase<UploadImagesParams, List<UploadedImageBySize>>(Dispatchers.IO) {

    override suspend fun execute(parameters: UploadImagesParams): List<UploadedImageBySize> {
        return coroutineScope {
            val images = parameters.images
            val storagePath = "Products/${parameters.year}/${parameters.category}/"

            images.map {
                val imageFile = createFileRepository.fileFromUri(it.uri)

                val largeImage = withContext(Dispatchers.IO) {
                    val largeImageFile = compressImageRepository.resizeImage(file = imageFile, size = SIZE.LARGE)

                    val imageUrl = uploadImageRepository.upload(
                            storagePath = storagePath,
                            fileName = largeImageFile.name.withPrefix("large"),
                            bytes = compressImageRepository.convertFileToByteArray(largeImageFile)
                    )

                    val imageSize = getImageSize(largeImageFile.toUri())

                    UploadedImageEntity(
                            w = imageSize.first,
                            h = imageSize.second,
                            url = imageUrl
                    )
                }

                val previewImageUrl = withContext(Dispatchers.IO) {
                    val previewImageFile = compressImageRepository.resizeImage(file = imageFile, size = SIZE.PREVIEW)

                    val imageUrl = uploadImageRepository.upload(
                            storagePath = storagePath,
                            fileName = previewImageFile.name.withPrefix("preview"),
                            bytes = compressImageRepository.convertFileToByteArray(previewImageFile)
                    )

                    val imageSize = getImageSize(previewImageFile.toUri())

                    UploadedImageEntity(
                            w = imageSize.first,
                            h = imageSize.second,
                            url = imageUrl
                    )
                }

                UploadedImageBySize(
                        preview = previewImageUrl,
                        fullSize = largeImage
                )
            }
        }
    }

    private fun getImageSize(uri: Uri): Pair<Int, Int> {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(File(uri.path).absolutePath, options)

        return options.outHeight to options.outWidth
    }

    private fun String.withPrefix(prefix: String): String {
        val withoutSuffix = removeSuffix(".jpg")

        return "${withoutSuffix}_$prefix.jpg"
    }
}