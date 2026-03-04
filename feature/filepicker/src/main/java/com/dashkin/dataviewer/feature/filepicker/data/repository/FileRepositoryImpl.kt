package com.dashkin.dataviewer.feature.filepicker.data.repository

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.dashkin.dataviewer.core.parser.result.DataViewerException
import com.dashkin.dataviewer.core.parser.result.Result
import com.dashkin.dataviewer.feature.filepicker.domain.model.FileFormat
import com.dashkin.dataviewer.feature.filepicker.domain.model.FileInfo
import com.dashkin.dataviewer.feature.filepicker.domain.repository.FileRepository

private const val PREFS_NAME = "recent_files"
private const val PREFS_KEY_URIS = "recent_uris"
private const val MAX_RECENT_FILES = 10

class FileRepositoryImpl(private val context: Context) : FileRepository {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override suspend fun getFileInfo(uri: String): Result<FileInfo> = runCatching {
        val contentUri = Uri.parse(uri)
        val cursor = context.contentResolver.query(
            contentUri,
            arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE),
            null,
            null,
            null
        ) ?: return Result.Error(DataViewerException.FileNotFoundException(uri))

        cursor.use {
            if (!it.moveToFirst()) return Result.Error(DataViewerException.FileNotFoundException(uri))
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = it.getColumnIndex(OpenableColumns.SIZE)
            val name = if (nameIndex >= 0) it.getString(nameIndex) else "unknown"
            val size = if (sizeIndex >= 0 && !it.isNull(sizeIndex)) it.getLong(sizeIndex) else 0L
            val format = detectFormat(name)
            Result.Success(FileInfo(uri = uri, name = name, sizeBytes = size, format = format))
        }
    }.getOrElse { throwable ->
        Result.Error(DataViewerException.UnknownException(throwable))
    }

    override suspend fun getRecentFiles(): Result<List<FileInfo>> = runCatching {
        val uris = prefs.getStringSet(PREFS_KEY_URIS, emptySet()) ?: emptySet()
        val files = uris.mapNotNull { uri ->
            (getFileInfo(uri) as? Result.Success)?.data
        }
        Result.Success(files)
    }.getOrElse { throwable ->
        Result.Error(DataViewerException.UnknownException(throwable))
    }

    override suspend fun saveRecentFile(fileInfo: FileInfo): Result<Unit> = runCatching {
        val current = prefs.getStringSet(PREFS_KEY_URIS, mutableSetOf())?.toMutableSet()
            ?: mutableSetOf()
        current.add(fileInfo.uri)
        if (current.size > MAX_RECENT_FILES) {
            val trimmed = current.toList().takeLast(MAX_RECENT_FILES).toSet()
            prefs.edit().putStringSet(PREFS_KEY_URIS, trimmed).apply()
        } else {
            prefs.edit().putStringSet(PREFS_KEY_URIS, current).apply()
        }
        Result.Success(Unit)
    }.getOrElse { throwable ->
        Result.Error(DataViewerException.UnknownException(throwable))
    }

    private fun detectFormat(fileName: String): FileFormat = when {
        fileName.endsWith(".json", ignoreCase = true) -> FileFormat.JSON
        fileName.endsWith(".xml", ignoreCase = true) -> FileFormat.XML
        else -> FileFormat.UNKNOWN
    }
}
