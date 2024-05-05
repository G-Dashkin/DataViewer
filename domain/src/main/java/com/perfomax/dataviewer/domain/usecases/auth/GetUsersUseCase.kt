package com.perfomax.dataviewer.domain.usecases.auth

import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.models.User
import com.perfomax.dataviewer.domain.repository.AuthRepository
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: AuthRepository
): UseCaseWithoutParams<List<User>> {
    override suspend fun execute(): List<User> {
        return repository.getAll()
    }
}