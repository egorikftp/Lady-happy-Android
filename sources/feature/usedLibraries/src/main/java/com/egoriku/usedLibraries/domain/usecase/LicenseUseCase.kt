package com.egoriku.usedLibraries.domain.usecase

import android.content.Context
import com.egoriku.network.usecase.UseCase
import com.egoriku.usedLibraries.domain.model.License
import com.egoriku.usedLibraries.domain.parser.MetadataParser
import kotlinx.coroutines.Dispatchers

class LicenseUseCase(private val context: Context) : UseCase<Unit, List<License>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<License> {
        val isLibrariesDataAvailable = fileExistsAndNotEmpty(context, "third_party_licenses")
                && fileExistsAndNotEmpty(context, "third_party_license_metadata")

        return if (!isLibrariesDataAvailable) {
            throw Exception("Licenses file not available")
        } else {
            MetadataParser(context).getLicenses()
        }
    }

    private fun fileExistsAndNotEmpty(context: Context, fileName: String): Boolean = try {
        context.resources.openRawResource(context.resources.getIdentifier(fileName, "raw", context.packageName)).use {
            it.available() > 0
        }
    } catch (e: Exception) {
        false
    }
}