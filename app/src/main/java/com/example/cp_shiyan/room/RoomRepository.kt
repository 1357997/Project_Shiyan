package com.example.cp_shiyan.room

interface RoomRepository {

    suspend fun insertItem(itemName: String)
    suspend fun getAllItems(): List<ItemEntity>
}