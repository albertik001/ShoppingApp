package com.geektech.shoppingapp.di.module

import com.geektech.shoppingapp.data.repository.ShopListRepositoryImpl
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepos(shopListRepository: ShopListRepository): ShopListRepository{
        return shopListRepository
    }

}