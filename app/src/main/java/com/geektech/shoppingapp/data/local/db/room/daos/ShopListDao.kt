package com.geektech.shoppingapp.data.local.db.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.geektech.shoppingapp.data.dto.ShopItemDto

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shopitemdto")
    fun getAllShopItems(): LiveData<List<ShopItemDto>>

    @Insert
    suspend fun addShopItem(shopItemDto: ShopItemDto)

    @Update
    suspend fun editShopItem(shopItemDto: ShopItemDto)

    @Delete
    suspend fun deleteShopItem(shopItemDto: ShopItemDto)

    @Query("SELECT * FROM shopitemdto WHERE id = :itemId")
    fun getItemById(itemId: Int): ShopItemDto

}