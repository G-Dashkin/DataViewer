package com.perfomax.dataviewer.domain.usecases.auth

import com.perfomax.dataviewer.domain.repository.AuthRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    private val repository: AuthRepository
): UseCaseWithoutParams<String> {
    override suspend fun execute(): String {
        return repository.getAuth()
    }
}