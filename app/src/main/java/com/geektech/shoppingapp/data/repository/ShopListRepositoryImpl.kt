package com.geektech.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.geektech.shoppingapp.data.local.ShopItemMapper
import com.geektech.shoppingapp.data.local.ShopListDao
import com.geektech.shoppingapp.domain.entity.ShopItem
import com.geektech.shoppingapp.domain.repository.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(private val shopListDao: ShopListDao) :
    ShopListRepository {

    private val mapper = ShopItemMapper()
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 ->
        o1.id.compareTo(o2.id)
    })

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0

    override suspend fun addShopItem(shopItem: ShopItem) {
/*        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()*/
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun removeShopItem(shopItem: ShopItem) {
/*        shopList.remove(shopItem)
        updateList()*/
        shopListDao.deleteShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
/*        val oldElement = getShopItem(shopItem.id)
        removeShopItem(oldElement)
        addShopItem(shopItem)*/
        shopListDao.editShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("element not found")
    }

    override suspend fun getShopList(): LiveData<List<ShopItem>> =
        Transformations.map(shopListDao.getAllShopItems()) {
            mapper.mapListDbModelToListEntity(it)
        }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}