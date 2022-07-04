package com.geektech.shoppingapp.data.mapper

import com.geektech.shoppingapp.data.dto.ShopItemDto
import com.geektech.shoppingapp.domain.entity.ShopItemModel

class ShopItemMapper {

    fun mapEntityToDbModel(shopItem: ShopItemModel): ShopItemDto {
        return ShopItemDto(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enable = shopItem.enable
        )
    }

    fun mapDbModelToEntity(shopItemDto: ShopItemDto): ShopItemModel {
        return ShopItemModel(
            id = shopItemDto.id,
            name = shopItemDto.name,
            count = shopItemDto.count,
            enable = shopItemDto.enable
        )
    }

    fun mapListDbModelToListEntity(list: List<ShopItemDto>) =
        list.map { shopItemDto -> mapDbModelToEntity(shopItemDto) }

}