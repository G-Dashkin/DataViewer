package com.perfomax.dataviewer.domain.usecases.auth

import com.perfomax.dataviewer.domain.repository.AuthRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class SetAuthUseCase @Inject constructor(
    private val repository: AuthRepository
): UseCaseWithParams<Unit, String> {
    override suspend fun execute(userName: String) {
        repository.setAuth(userName = userName)
    }
}