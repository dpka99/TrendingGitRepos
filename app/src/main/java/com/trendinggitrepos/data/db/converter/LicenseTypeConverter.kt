package com.trendinggitrepos.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.trendinggitrepos.data.model.License

class LicenseTypeConverter {

    @TypeConverter
    fun licenseToString(license: License?): String = Gson().toJson(license)

    @TypeConverter
    fun stringToLicense(string: String): License? = Gson().fromJson(string, License::class.java)
}