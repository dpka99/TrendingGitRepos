package com.trendinggitrepos.presentation.di

import android.content.Context
import androidx.room.Room
import com.trendinggitrepos.data.db.RepoDao
import com.trendinggitrepos.data.db.ReposRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideGithubRoomDatabase(context: Context): ReposRoomDatabase {
        return Room.databaseBuilder(
            context,
            ReposRoomDatabase::class.java,
            "TrendingRepos"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGoalDao(githubRoomDatabase: ReposRoomDatabase): RepoDao {
        return githubRoomDatabase.getRepoDao()
    }


}