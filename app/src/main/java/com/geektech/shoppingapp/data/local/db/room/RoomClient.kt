package com.geektech.shoppingapp.data.local.db.room

import android.content.Context
import androidx.room.Room
import com.geektech.shoppingapp.data.local.db.room.daos.ShopListDao
import com.geektech.shoppingapp.data.local.db.room.database.AppDataBase

class RoomClient {

    fun provideRoomDatabase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "database")

            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

    fun provideShopListDao(appDataBase: AppDataBase): ShopListDao = appDataBase.shopListDao()

}