package com.egoriku.ladyhappy.postcreator.domain.usecase

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import com.egoriku.ladyhappy.network.usecase.UseCase
import com.egoriku.ladyhappy.postcreator.data.entity.UploadedImageEntity
import com.egoriku.ladyhappy.postcreator.data.local.CompressImageRepository
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
) : UseCase<UploadImagesParams, List<UploadedImageEntity>>(Dispatchers.IO) {

    override suspend fun execute(parameters: UploadImagesParams): List<UploadedImageEntity> {
        return coroutineScope {
            val images = parameters.images
            val storagePath = "Products/${parameters.year}/${parameters.category}/"

            images.map {
                val imageFile = createFileRepository.fileFromUri(it.uri)

                withContext(Dispatchers.IO) {
                    val resizedImageFile = compressImageRepository.resizeImage(file = imageFile)

                    val imageUrl = uploadImageRepository.upload(
                            storagePath = storagePath,
                            fileName = resizedImageFile.name,
                            bytes = compressImageRepository.convertFileToByteArray(resizedImageFile)
                    )

                    val imageSize = getImageSize(resizedImageFile.toUri())

                    UploadedImageEntity(
                            w = imageSize.first,
                            h = imageSize.second,
                            url = imageUrl
                    )
                }
            }
        }
    }

    private fun getImageSize(uri: Uri): Pair<Int, Int> {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(File(uri.path).absolutePath, options)

        return options.outWidth to options.outHeight
    }
}