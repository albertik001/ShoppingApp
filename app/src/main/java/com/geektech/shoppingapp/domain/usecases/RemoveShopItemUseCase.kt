package com.geektech.shoppingapp.domain.usecases

import com.geektech.shoppingapp.domain.entity.ShopItemModel
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class RemoveShopItemUseCase @Inject constructor(private val repository: ShopListRepository) {

    suspend fun removeShopItem(shopItem: ShopItemModel) {
        repository.removeShopItem(shopItem)
    }
}