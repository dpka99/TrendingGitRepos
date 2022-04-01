package com.trendinggitrepos.domain.usecase

import com.trendinggitrepos.data.model.RepoItem
import com.trendinggitrepos.domain.repository.GithubReposRepository

class GetGithubReposUseCase(private val githubReposRepository: GithubReposRepository) {

    suspend fun execute(): List<RepoItem>? = githubReposRepository.getAllRepositories()

}