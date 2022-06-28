package com.geektech.shoppingapp.domain.usecases

import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val repository: ShopListRepository) {

    fun getShopList() = repository.getShopList()

}