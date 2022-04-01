package com.trendinggitrepos.data.repository.datasource

import com.trendinggitrepos.data.model.GithubApiResponse
import retrofit2.Response

interface GithubRepoRemoteDatasource {


    suspend fun getRemoteGitKotlinRepositories(): Response<GithubApiResponse>
    suspend fun getRemoteGitJavaRepositories(): Response<GithubApiResponse>

}