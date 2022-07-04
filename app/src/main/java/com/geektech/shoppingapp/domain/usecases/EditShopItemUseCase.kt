package com.geektech.shoppingapp.domain.usecases

import com.geektech.shoppingapp.domain.entity.ShopItemModel
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(private val repository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItemModel) {
        repository.editShopItem(shopItem)
    }
}