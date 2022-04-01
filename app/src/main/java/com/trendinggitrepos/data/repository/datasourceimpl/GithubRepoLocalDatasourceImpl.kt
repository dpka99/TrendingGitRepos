package com.trendinggitrepos.data.repository.datasourceimpl

import com.trendinggitrepos.data.db.RepoDao
import com.trendinggitrepos.data.model.RepoItem
import com.trendinggitrepos.data.repository.datasource.GithubRepoLocalDatasource


class GithubRepoLocalDatasourceImpl(private val repoDao: RepoDao) : GithubRepoLocalDatasource {

    override suspend fun getRepositoriesFromDB(): List<RepoItem> = repoDao.getAllRepositories()


    override suspend fun saveRepositoriesToDB(repositories: List<RepoItem>) {
        repoDao.saveGithubRepositories(repositories)
    }

    override suspend fun getRepositoryById(nodeId: String): RepoItem =
        repoDao.getRepositoryById(nodeId)


    override suspend fun clearAll() {
        repoDao.deleteAll()
    }

}