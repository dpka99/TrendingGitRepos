package com.trendinggitrepos.presentation.di

import android.content.Context
import com.trendinggitrepos.domain.repository.GithubReposRepository
import com.trendinggitrepos.domain.usecase.GetGithubReposUseCase
import com.trendinggitrepos.domain.usecase.GetSingleRepoUseCase
import com.trendinggitrepos.domain.usecase.SyncDataUseCase
import com.trendinggitrepos.presentation.repolist.GithubRepoViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetGitHubReposUseCase(githubReposRepository: GithubReposRepository): GetGithubReposUseCase {
        return GetGithubReposUseCase(githubReposRepository)
    }

    @Provides
    @Singleton
    fun provideGetSingleRepoUseCase(githubReposRepository: GithubReposRepository): GetSingleRepoUseCase {
        return GetSingleRepoUseCase(githubReposRepository)
    }

    @Provides
    @Singleton
    fun provideSynDataUseCase(context: Context): SyncDataUseCase {
        return SyncDataUseCase(context)
    }


}