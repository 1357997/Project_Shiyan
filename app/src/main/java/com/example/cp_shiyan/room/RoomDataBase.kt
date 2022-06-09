package com.example.cp_shiyan.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        private var INSTANCE: RoomDataBase? = null

        fun getInstance(context: Context): RoomDataBase? {
            if (INSTANCE == null) {
                synchronized(RoomDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RoomDataBase::class.java, "item.db"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}