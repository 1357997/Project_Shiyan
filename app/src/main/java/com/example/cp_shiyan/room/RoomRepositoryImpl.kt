package com.example.cp_shiyan.room

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class RoomRepositoryImpl(context: Context) : RoomRepository {

    val dao = RoomDataBase.getInstance(context)?.dao()!!
    private val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm");
    private val resultDate = Date(System.currentTimeMillis());

    override suspend fun insertItem(itemName: String) {
        dao.insertItem(
            ItemEntity(
                itemName = itemName,
                createdAt = sdf.format(resultDate),
                imagePath = "/storage/emulated/0/DCIM"
            )
        )
    }

    override suspend fun getAllItems(): List<ItemEntity> {
        return dao.getAllItems()
    }
}