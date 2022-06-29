package com.geektech.shoppingapp.di.module

import com.geektech.shoppingapp.data.repository.ShopListRepositoryImpl
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsRepository(shopListRepositoryImpl: ShopListRepositoryImpl): ShopListRepository

}