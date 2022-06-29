package com.geektech.shoppingapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.geektech.shoppingapp.data.dto.ShopItemDto

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shopitemdto")
    fun getAllShopItems(): LiveData<List<ShopItemDto>>

    @Insert
    fun addShopItem(shopItemDto: ShopItemDto)

    @Update
    fun editShopItem(shopItemDto: ShopItemDto)
}