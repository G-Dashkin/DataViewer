package com.perfomax.dataviewer.domain.usecases.auth

import com.perfomax.dataviewer.domain.repository.AuthRepository
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
): UseCaseWithParams<Unit, String> {
    override suspend fun execute(newUser: String) {
        repository.create(newUser = newUser)
    }
}