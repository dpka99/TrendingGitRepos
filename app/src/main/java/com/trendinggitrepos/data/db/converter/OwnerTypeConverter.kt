package com.trendinggitrepos.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.trendinggitrepos.data.model.Owner

class OwnerTypeConverter {

    @TypeConverter
    fun ownerToString(owner: Owner?): String? = Gson().toJson(owner)

    @TypeConverter
    fun stringToOwner(string: String): Owner? = Gson().fromJson(string, Owner::class.java)
}