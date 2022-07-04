package com.geektech.shoppingapp.presentation.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geektech.shoppingapp.domain.entity.ShopItemModel
import com.geektech.shoppingapp.domain.usecases.*
import com.geektech.shoppingapp.presentation.models.ShopItemUI
import com.geektech.shoppingapp.presentation.models.toUi
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

    fun addShopItem(shopItem: ShopItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    suspend fun getShopList(): LiveData<List<ShopItemUI>> =
        Transformations.map(getShopListUseCase.getShopList()) { shopItemModelList ->
            shopItemModelList.map { it.toUi() }
        }

    fun checkItemCount(
        size: Int,
        actionWhenThereAreNoItems: () -> Unit,
        actionWhenThereAreItems: () -> Unit
    ) {
        when (size) {
            0 -> actionWhenThereAreNoItems()
            else -> actionWhenThereAreItems()
        }
    }


    fun removeShopItem(shopItem: ShopItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeShopItemUseCase.removeShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = shopItem.copy(enable = !shopItem.enable)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    suspend fun getShopItem(shopItemId: Int) = getShopItemUseCase.getShopItem(shopItemId).toUi()

}

