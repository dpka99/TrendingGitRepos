package com.trendinggitrepos.presentation.di

import androidx.work.Configuration
import com.trendinggitrepos.workmanager.DaggerWorkerFactory
import com.trendinggitrepos.domain.repository.GithubReposRepository
import com.trendinggitrepos.domain.usecase.GetGithubReposUseCase
import com.trendinggitrepos.domain.usecase.GetSingleRepoUseCase
import com.trendinggitrepos.domain.usecase.SyncDataUseCase
import com.trendinggitrepos.presentation.repolist.GithubRepoViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FactoryModule {

    @Provides
    @Singleton
    fun provideGithubRepoViewModelFactory(
        getGithubReposUseCase: GetGithubReposUseCase,
        getSingleRepoUseCase: GetSingleRepoUseCase,
        syncDataUseCase: SyncDataUseCase
    ): GithubRepoViewModelFactory {
        return GithubRepoViewModelFactory(
            getGithubReposUseCase,
            getSingleRepoUseCase,
            syncDataUseCase
        )
    }
}