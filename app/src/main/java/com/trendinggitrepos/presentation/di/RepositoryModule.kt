package com.trendinggitrepos.presentation.di

import com.trendinggitrepos.data.api.GithubApiEndPoint
import com.trendinggitrepos.data.db.RepoDao
import com.trendinggitrepos.data.repository.GithubReposRepositoryImpl
import com.trendinggitrepos.data.repository.datasource.GithubRepoLocalDatasource
import com.trendinggitrepos.data.repository.datasource.GithubRepoRemoteDatasource
import com.trendinggitrepos.data.repository.datasourceimpl.GithubRepoLocalDatasourceImpl
import com.trendinggitrepos.data.repository.datasourceimpl.GithubRepoRemoteDatasourceImpl
import com.trendinggitrepos.domain.repository.GithubReposRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteDatasource(githubApiEndPointInterface: GithubApiEndPoint)
            : GithubRepoRemoteDatasource {
        return GithubRepoRemoteDatasourceImpl(githubApiEndPointInterface)
    }

    @Provides
    @Singleton
    fun provideLocalDatasoure(repoDao: RepoDao): GithubRepoLocalDatasource {
        return GithubRepoLocalDatasourceImpl(repoDao)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(
        githubRepoRemoteDatasource: GithubRepoRemoteDatasource,
        githubRepoLocalDatasource: GithubRepoLocalDatasource
    ): GithubReposRepository {
        return GithubReposRepositoryImpl(githubRepoRemoteDatasource, githubRepoLocalDatasource)
    }

}