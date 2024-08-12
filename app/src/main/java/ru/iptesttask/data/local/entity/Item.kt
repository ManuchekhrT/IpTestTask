package ru.iptesttask.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.iptesttask.data.local.db.TagsConverter

@TypeConverters(TagsConverter::class)
@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)