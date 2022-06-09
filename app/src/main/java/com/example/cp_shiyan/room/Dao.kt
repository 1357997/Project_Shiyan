package com.example.cp_shiyan.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemEntity: ItemEntity)

    @Query("SELECT * FROM itemInfo ORDER BY created_at")
    suspend fun getAllItems(): List<ItemEntity>
}