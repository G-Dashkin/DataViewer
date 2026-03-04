package com.dashkin.dataviewer.feature.filepicker.domain.model

/**
 * Represents metadata about a file selected by the user.
 *
 * @param uri Content URI string from Storage Access Framework.
 * @param name Display name of the file.
 * @param sizeBytes File size in bytes.
 * @param format Detected file format.
 */
data class FileInfo(
    val uri: String,
    val name: String,
    val sizeBytes: Long,
    val format: FileFormat
)

enum class FileFormat {
    JSON, XML, UNKNOWN
}
