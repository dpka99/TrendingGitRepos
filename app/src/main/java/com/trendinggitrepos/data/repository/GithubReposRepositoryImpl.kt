package com.trendinggitrepos.data.repository

import android.util.Log
import com.trendinggitrepos.data.model.RepoItem
import com.trendinggitrepos.data.repository.datasource.GithubRepoLocalDatasource
import com.trendinggitrepos.data.repository.datasource.GithubRepoRemoteDatasource
import com.trendinggitrepos.domain.repository.GithubReposRepository
import java.lang.Exception



class GithubReposRepositoryImpl(
    private val remoteDatasource: GithubRepoRemoteDatasource,
    private val localDatasource: GithubRepoLocalDatasource
) : GithubReposRepository {

    var TAG = "GithubReposRepositoryImpl"

    override suspend fun getAllRepositories(): List<RepoItem> {

        lateinit var githubRepoList: List<RepoItem>
        try {
            githubRepoList = getRepositoriesFromDB()
        } catch (exception: Exception) {
            Log.e(TAG, " LIne#23 => ${exception.message.toString()}")
        }
        return githubRepoList
    }

    override suspend fun getSingleRepositoryById(nodeId: String): RepoItem =
        localDatasource.getRepositoryById(nodeId)

    override suspend fun syncData() {

        try {
            val response = remoteDatasource.getRemoteGitJavaRepositories()
            Log.e("syncData", response.toString())

            Log.e(TAG, "RESPONSE URL = ${response.raw().request().url().toString()}")
            Log.e(TAG, "isSuccessful. = ${response.isSuccessful}")

            val body = response.body()
            if (body != null) {
                val githubReposList = body.items
                localDatasource.clearAll()
                Log.e("syncData", githubReposList?.size.toString())
                localDatasource.saveRepositoriesToDB(githubReposList)
            }
        } catch (exception: java.lang.Exception) {
            Log.e(TAG, exception.message.toString())
        }
    }

    suspend fun getRepositoriesFromDB(): List<RepoItem> {
        lateinit var githubReposList: List<RepoItem>
        try {
            githubReposList = localDatasource.getRepositoriesFromDB()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (githubReposList.size > 0) {
            return githubReposList
        } else {

            githubReposList = getRepositoriesFromAPI()
            if (githubReposList.isNotEmpty()) {
                localDatasource.saveRepositoriesToDB(githubReposList)
            }
        }

        return githubReposList
    }


    private suspend fun getRepositoriesFromAPI(): List<RepoItem> {
        lateinit var githubReposList: List<RepoItem>
        try {
            val response = remoteDatasource.getRemoteGitKotlinRepositories()

            Log.e(TAG, "RESPONSE URL = ${response.raw().request().url().toString()}")
            Log.e(TAG, "isSuccessful. = ${response.isSuccessful}")

            val body = response.body()
            if (body != null) {
                githubReposList = body.items
            }
        } catch (exception: Throwable) {
            Log.e(TAG, "line85 ${exception.message.toString()}")
            githubReposList = emptyList()
        }

        return githubReposList
    }

}