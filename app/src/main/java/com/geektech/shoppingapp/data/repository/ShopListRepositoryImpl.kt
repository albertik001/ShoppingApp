package com.geektech.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.geektech.shoppingapp.data.dto.toData
import com.geektech.shoppingapp.data.dto.toDomain
import com.geektech.shoppingapp.data.local.db.room.daos.ShopListDao
import com.geektech.shoppingapp.data.mapper.ShopItemMapper
import com.geektech.shoppingapp.domain.entity.ShopItemModel
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(private val shopListDao: ShopListDao) :
    ShopListRepository {

    override suspend fun addShopItem(shopItem: ShopItemModel) {
        shopListDao.addShopItem(shopItem.toData())
    }

    override suspend fun removeShopItem(shopItem: ShopItemModel) {
        shopListDao.deleteShopItem(shopItem.toData())
    }

    override suspend fun editShopItem(shopItem: ShopItemModel) {
        shopListDao.editShopItem(shopItem.toData())
    }

    override suspend fun getShopItem(shopItemId: Int) =
        shopListDao.getItemById(shopItemId).toDomain()

    override suspend fun getShopList(): LiveData<List<ShopItemModel>> =
        Transformations.map(shopListDao.getAllShopItems()) { shopItemListDto ->
            shopItemListDto.map { it.toDomain() }
        }


}