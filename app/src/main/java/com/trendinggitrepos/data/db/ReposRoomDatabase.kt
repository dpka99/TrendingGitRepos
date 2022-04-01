package com.trendinggitrepos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trendinggitrepos.data.db.converter.LicenseTypeConverter
import com.trendinggitrepos.data.db.converter.OwnerTypeConverter
import com.trendinggitrepos.data.db.converter.TopicsTypeConverter
import com.trendinggitrepos.data.model.RepoItem


@Database(entities = [RepoItem::class], version = 1, exportSchema = false)
@TypeConverters(LicenseTypeConverter::class, OwnerTypeConverter::class, TopicsTypeConverter::class)
abstract class ReposRoomDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RepoDao

}
