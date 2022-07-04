package com.geektech.shoppingapp.domain.repository

import androidx.lifecycle.LiveData
import com.geektech.shoppingapp.domain.entity.ShopItemModel

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItemModel)

    suspend fun removeShopItem(shopItem: ShopItemModel)

    suspend fun editShopItem(shopItem: ShopItemModel)

    suspend fun getShopItem(shopItemId: Int): ShopItemModel

    suspend fun getShopList(): LiveData<List<ShopItemModel>>

}