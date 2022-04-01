package com.trendinggitrepos.data.api

import com.trendinggitrepos.data.model.GithubApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface GithubApiEndPoint {

    @GET("search/repositories?q=android+language:kotlin&sort=stars&order=desc")
    suspend fun getGithubRespositories(): Response<GithubApiResponse>

    @GET("search/repositories?q=android+language:java&sort=stars&order=desc")
    suspend fun getGithubJavaRespositories(): Response<GithubApiResponse>

}