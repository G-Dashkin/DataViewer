package com.perfomax.dataviewer.domain.usecases.settings

import com.perfomax.dataviewer.domain.repository.SettingsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetUpdatePeriodUseCase @Inject constructor(
    private val repository: SettingsRepository
): UseCaseWithoutParams<String> {
    override suspend fun execute(): String {
        return repository.getUpdatePeriod()
    }
}