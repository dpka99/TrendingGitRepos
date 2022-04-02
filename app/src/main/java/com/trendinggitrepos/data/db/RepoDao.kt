package com.trendinggitrepos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trendinggitrepos.data.model.RepoItem

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGithubRepositories(repositories: List<RepoItem>)

    @Query("SELECT * FROM RepoTable")
    suspend fun getAllRepositories(): List<RepoItem>

    @Query("SELECT * FROM repotable WHERE nodeId IN (:nodeId)")
    suspend fun getRepositoryById(nodeId: String): RepoItem

    @Query("DELETE FROM RepoTable")
    suspend fun deleteAll()


}