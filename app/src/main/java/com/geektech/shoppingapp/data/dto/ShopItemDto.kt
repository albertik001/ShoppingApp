package com.geektech.shoppingapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItemDto(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String?,
    val count: Int?,
    val enable: Boolean
)
