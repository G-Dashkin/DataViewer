package com.perfomax.dataviewer.domain.usecases.settings

import com.perfomax.dataviewer.domain.repository.SettingsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class SetUpdatePeriodUseCase @Inject constructor(
    private val repository: SettingsRepository
): UseCaseWithParams<Unit, String> {
    override suspend fun execute(params: String) {
        repository.setUpdatePeriod(params)
    }
}