package com.geektech.shoppingapp.presentation.models

import com.geektech.shoppingapp.domain.entity.ShopItemModel

data class ShopItemUI(
    val name: String?,
    val count: Int?,
    val enable: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}

fun ShopItemModel.toUi() = ShopItemUI(name, count, enable, id)
fun ShopItemUI.toDomain() = ShopItemModel(name,count,enable,id)