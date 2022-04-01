package com.trendinggitrepos.presentation.repolist

import android.util.Log
import androidx.lifecycle.*
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.trendinggitrepos.data.model.RepoItem
import com.trendinggitrepos.domain.usecase.GetGithubReposUseCase
import com.trendinggitrepos.domain.usecase.GetSingleRepoUseCase
import com.trendinggitrepos.domain.usecase.SyncDataUseCase
import kotlinx.coroutines.launch

class GithubRepoViewModel(

    private val getGithubReposUseCase: GetGithubReposUseCase,
    private val getSingleRepoUseCase: GetSingleRepoUseCase,
    private val syncDataUseCase: SyncDataUseCase) : ViewModel() {

    init {
        syncDataUseCase.execute()
    }

    fun getReposData() = liveData {
        val result = getGithubReposUseCase.execute()
        emit(result)
    }

    fun getRepositoryByNodeId(nodeId: String) = liveData {
        val result = getSingleRepoUseCase.execute(nodeId)
        emit(result)
    }

//    fun getSyncOutput() = liveData {
//        val result  = syncDataUseCase.execute()
//        emit(result)
//    }

}