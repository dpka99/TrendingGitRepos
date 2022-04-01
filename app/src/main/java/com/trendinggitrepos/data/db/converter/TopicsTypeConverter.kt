package com.trendinggitrepos.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trendinggitrepos.data.model.Owner
import java.util.*
import java.util.Collections.emptyList

class TopicsTypeConverter {

    @TypeConverter
    fun listToString(data: List<String>?) : String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun stringToList(data: String?): List<String>{
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson<List<String>>(data,listType)

    }
}