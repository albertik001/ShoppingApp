package com.geektech.shoppingapp.di.module

import android.content.Context
import com.geektech.shoppingapp.data.local.db.room.database.AppDataBase
import com.geektech.shoppingapp.data.local.db.room.RoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    private val roomClient = RoomClient()

    @Singleton
    @Provides
    fun provideShopListDao(appDatabase: AppDataBase) =
        roomClient.provideShopListDao(appDatabase)

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        roomClient.provideRoomDatabase(context)

}