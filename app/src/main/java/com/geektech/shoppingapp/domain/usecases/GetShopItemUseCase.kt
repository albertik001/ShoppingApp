package com.geektech.shoppingapp.domain.usecases

import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val repository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int) = repository.getShopItem(shopItemId)

}