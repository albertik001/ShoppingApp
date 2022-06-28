package com.geektech.shoppingapp.presentation.ui.activity.main

import androidx.lifecycle.ViewModel
import com.geektech.shoppingapp.data.repository.ShopListRepositoryImpl
import com.geektech.shoppingapp.domain.entity.ShopItem
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import com.geektech.shoppingapp.domain.usecases.*
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : ShopListRepository
) : ViewModel() {

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun getShopList() = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enable = !shopItem.enable)
        println(newItem.enable)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun getShopItem(shopItem: ShopItem): ShopItem {
        return getShopItemUseCase.getShopItem(shopItem)
    }
}