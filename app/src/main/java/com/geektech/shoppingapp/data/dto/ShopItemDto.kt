package com.geektech.shoppingapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.geektech.shoppingapp.domain.entity.ShopItemModel

@Entity()
data class ShopItemDto(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String?,
    val count: Int?,
    val enable: Boolean
)

fun ShopItemDto.toDomain() = ShopItemModel(name, count, enable, id)
fun ShopItemModel.toData() = ShopItemDto(id, name, count, enable)
