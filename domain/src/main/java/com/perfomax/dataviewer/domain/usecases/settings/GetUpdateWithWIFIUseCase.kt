package com.perfomax.dataviewer.domain.usecases.settings

import com.perfomax.dataviewer.domain.repository.SettingsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetUpdateWithWIFIUseCase @Inject constructor(
    private val repository: SettingsRepository
): UseCaseWithoutParams<Boolean> {
    override suspend fun execute(): Boolean {
        return repository.getUpdateWithWIFI()
    }
}