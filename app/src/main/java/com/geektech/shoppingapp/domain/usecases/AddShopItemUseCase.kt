package com.geektech.shoppingapp.domain.usecases

import com.geektech.shoppingapp.domain.entity.ShopItem
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(private val repository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        repository.addShopItem(shopItem)
    }
}