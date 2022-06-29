package com.geektech.shoppingapp.data.local.db

import android.content.Context
import androidx.room.Room
import com.geektech.shoppingapp.data.local.AppDataBase
import com.geektech.shoppingapp.data.local.ShopListDao

class RoomClient {

    fun provideRoomDatabase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "database")

            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

    fun provideShopListDao(appDataBase: AppDataBase): ShopListDao = appDataBase.shopListDao()

}