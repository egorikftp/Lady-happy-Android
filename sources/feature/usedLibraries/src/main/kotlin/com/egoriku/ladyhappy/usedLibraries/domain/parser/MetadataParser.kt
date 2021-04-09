package com.egoriku.ladyhappy.usedLibraries.domain.parser

import android.content.Context
import com.egoriku.ladyhappy.usedLibraries.domain.model.License
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import kotlin.math.min

private const val ONE_KB = 1024

class MetadataParser(private val context: Context) {

    fun getLicenses(): List<License> {
        return readFromFile(inputStream = context.openFile("third_party_license_metadata"))
                .split("\n".toRegex())
                .filter { it.isNotEmpty() }
                .map { str ->
                    val indexOfSpace = str.indexOf(" ")
                    val licenseRegion = str
                            .substring(0, indexOfSpace)
                            .split(":".toRegex())
                            .toTypedArray()

                    val libraryName = str.substring(indexOfSpace + 1)
                    val licenseText = readFromFile(
                            inputStream = context.openFile("third_party_licenses"),
                            skipBytes = licenseRegion[0].toLong(),
                            length = licenseRegion[1].toInt()
                    )

                    License(libraryName = libraryName, libraryLicense = licenseText)
                }
                .sortedWith { o1, o2 -> o1.libraryName.compareTo(o2.libraryName, ignoreCase = true) }
    }

    private fun Context.openFile(name: String): InputStream =
            resources.openRawResource(resources.getIdentifier(name, "raw", context.packageName))

    private fun readFromFile(inputStream: InputStream, skipBytes: Long = 0, length: Int = -1): String {
        var lengthOffset = length
        val bytes = ByteArray(ONE_KB)
        val byteArrayOutputStream = ByteArrayOutputStream()

        return byteArrayOutputStream.use {
            inputStream.use {
                try {
                    inputStream.skip(skipBytes)

                    if (lengthOffset <= 0) {
                        lengthOffset = Int.MAX_VALUE
                    }

                    while (lengthOffset > 0) {
                        val read = inputStream.read(bytes, 0, min(lengthOffset, ONE_KB))
                        if (read == -1) {
                            break
                        }
                        byteArrayOutputStream.write(bytes, 0, read)
                        lengthOffset -= read
                    }

                    try {
                        byteArrayOutputStream.toString("UTF-8")
                    } catch (e: UnsupportedEncodingException) {
                        throw RuntimeException("Unsupported encoding UTF8. This should always be supported.", e)
                    }
                } catch (ioException: IOException) {
                    throw RuntimeException("Failed to read license or metadata text.", ioException)
                }
            }
        }
    }
}