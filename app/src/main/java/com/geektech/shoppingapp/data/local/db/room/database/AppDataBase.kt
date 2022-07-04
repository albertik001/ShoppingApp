package com.geektech.shoppingapp.data.local.db.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.shoppingapp.data.dto.ShopItemDto
import com.geektech.shoppingapp.data.local.db.room.daos.ShopListDao

@Database(entities = [ShopItemDto::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun shopListDao(): ShopListDao
}