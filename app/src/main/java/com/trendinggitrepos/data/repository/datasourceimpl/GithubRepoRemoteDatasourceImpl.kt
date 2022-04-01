package com.trendinggitrepos.data.repository.datasourceimpl

import com.trendinggitrepos.data.api.GithubApiEndPoint
import com.trendinggitrepos.data.model.GithubApiResponse
import com.trendinggitrepos.data.repository.datasource.GithubRepoRemoteDatasource
import retrofit2.Response

class GithubRepoRemoteDatasourceImpl(
    val githubApiEndPoint: GithubApiEndPoint
) : GithubRepoRemoteDatasource {

    override suspend fun getRemoteGitKotlinRepositories(): Response<GithubApiResponse> =
        githubApiEndPoint.getGithubRespositories()

    override suspend fun getRemoteGitJavaRepositories(): Response<GithubApiResponse> =
        githubApiEndPoint.getGithubJavaRespositories()


}