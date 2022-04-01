package com.trendinggitrepos.data.model


import com.google.gson.annotations.SerializedName

data class GithubApiResponse(

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: ArrayList<RepoItem>,

    @SerializedName("total_count")
    val totalCount: Int
)