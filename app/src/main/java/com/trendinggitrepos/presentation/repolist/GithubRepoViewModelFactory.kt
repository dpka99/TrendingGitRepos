package com.trendinggitrepos.presentation.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trendinggitrepos.domain.usecase.GetGithubReposUseCase
import com.trendinggitrepos.domain.usecase.GetSingleRepoUseCase
import com.trendinggitrepos.domain.usecase.SyncDataUseCase

class GithubRepoViewModelFactory(
    private val getGithubReposUseCase: GetGithubReposUseCase,
    private val singleRepoUseCase: GetSingleRepoUseCase,
    private val syncDataUseCase: SyncDataUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubRepoViewModel(getGithubReposUseCase, singleRepoUseCase, syncDataUseCase) as T
    }
}