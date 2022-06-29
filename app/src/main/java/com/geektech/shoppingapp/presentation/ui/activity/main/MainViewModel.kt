package com.geektech.shoppingapp.presentation.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geektech.shoppingapp.domain.entity.ShopItem
import com.geektech.shoppingapp.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addShopItemUseCase: AddShopItemUseCase,
    private val getShopListUseCase: GetShopListUseCase,
    private val removeShopItemUseCase: RemoveShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val getShopItemUseCase: GetShopItemUseCase
) : ViewModel() {

    fun addShopItem(shopItem: ShopItem) {
        viewModelScope.launch(Dispatchers.IO) {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    suspend fun getShopList() = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeShopItemUseCase.removeShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = shopItem.copy(enable = !shopItem.enable)
            editShopItemUseCase.editShopItem(newItem)
        }

    }

    fun getShopItem(shopItem: ShopItem) =
        viewModelScope.launch(Dispatchers.IO) { getShopItemUseCase.getShopItem(shopItem) }

}

