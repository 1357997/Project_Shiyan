package com.example.cp_shiyan.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemInfo")
data class ItemEntity(
    @PrimaryKey val itemName: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String
)
