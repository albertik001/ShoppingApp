package com.geektech.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.shoppingapp.data.dto.ShopItemDto

@Database(entities = [ShopItemDto::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun shopListDao(): ShopListDao
}