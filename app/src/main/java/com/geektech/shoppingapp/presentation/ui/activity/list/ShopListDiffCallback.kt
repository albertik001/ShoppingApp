package com.geektech.shoppingapp.presentation.ui.activity.list

import androidx.recyclerview.widget.DiffUtil
import com.geektech.shoppingapp.presentation.models.ShopItemUI

class ShopListDiffCallback(
    private val oldList: List<ShopItemUI>,
    private val newList: List<ShopItemUI>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}