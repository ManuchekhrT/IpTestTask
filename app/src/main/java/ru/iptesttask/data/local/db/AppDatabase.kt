package ru.iptesttask.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.iptesttask.data.local.entity.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(TagsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
