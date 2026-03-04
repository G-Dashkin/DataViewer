package com.dashkin.dataviewer.feature.filepicker.domain.repository

import com.dashkin.dataviewer.core.parser.result.Result
import com.dashkin.dataviewer.feature.filepicker.domain.model.FileInfo

interface FileRepository {
    suspend fun getFileInfo(uri: String): Result<FileInfo>
    suspend fun getRecentFiles(): Result<List<FileInfo>>
    suspend fun saveRecentFile(fileInfo: FileInfo): Result<Unit>
}
