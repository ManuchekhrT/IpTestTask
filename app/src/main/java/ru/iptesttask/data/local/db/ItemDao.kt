package ru.iptesttask.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.iptesttask.data.local.entity.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item WHERE name LIKE '%' || :query || '%'")
    fun searchItemsByName(query: String): Flow<List<Item>>

    @Query("SELECT * FROM item ORDER BY time")
    fun getAllItems(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Item>)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}
