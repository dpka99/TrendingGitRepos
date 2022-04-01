package com.trendinggitrepos.domain.repository

import com.trendinggitrepos.data.model.RepoItem

interface GithubReposRepository {

    suspend fun getAllRepositories(): List<RepoItem>?
    suspend fun getSingleRepositoryById(nodeId: String): RepoItem
    suspend fun syncData()
}