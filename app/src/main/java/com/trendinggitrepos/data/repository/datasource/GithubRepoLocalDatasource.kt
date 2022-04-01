package com.trendinggitrepos.data.repository.datasource

import com.trendinggitrepos.data.model.RepoItem

interface GithubRepoLocalDatasource {

    suspend fun getRepositoriesFromDB() : List<RepoItem>
    suspend fun saveRepositoriesToDB(repositories :List<RepoItem>)
    suspend fun getRepositoryById(nodeId : String) : RepoItem
    suspend fun clearAll()
}