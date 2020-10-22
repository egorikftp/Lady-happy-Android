package com.egoriku.ladyhappy.postcreator.data.local

import android.content.Context
import android.net.Uri
import com.egoriku.ladyhappy.postcreator.data.local.util.FileUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class CreateFileRepository(private val context: Context) {

    suspend fun fileFromUri(uri: Uri): File = withContext(Dispatchers.IO) {
        FileUtil.from(context, uri)
    }
}