package com.example.bobsbooks.models.database
import android.util.Log
import androidx.room.TypeConverter

class ArrayConverter {

    @TypeConverter
    fun stringToStringList(data: String?): List<String>? {
        return data?.let {
            it.split(",").map {
                try {
                    it.toString()
                } catch (ex: NumberFormatException) {
                    null
                }
            }
        }?.filterNotNull()
        Log.i("stringTo", " string to stringlist")
    }

    @TypeConverter
    fun stringListToString(strings: List<String>?): String? {
        return strings?.joinToString(",")
        Log.i("stringTo", " stringlist to string")
    }
}