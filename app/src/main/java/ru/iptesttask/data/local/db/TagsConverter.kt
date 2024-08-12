package ru.iptesttask.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagsConverter {
    @TypeConverter
    fun fromTagsList(tags: List<String>): String {
        return Gson().toJson(tags)
    }

    @TypeConverter
    fun toTagsList(tagsString: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(tagsString, listType)
    }
}
