package com.trendinggitrepos.domain.usecase

import com.trendinggitrepos.domain.repository.GithubReposRepository

class GetSingleRepoUseCase(private val reposRepository: GithubReposRepository) {

    suspend fun execute(nodeId: String) = reposRepository.getSingleRepositoryById(nodeId)
}