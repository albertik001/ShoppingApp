package com.geektech.shoppingapp.domain.repository

import androidx.lifecycle.LiveData
import com.geektech.shoppingapp.domain.entity.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun removeShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

    suspend fun getShopList(): LiveData<List<ShopItem>>

}