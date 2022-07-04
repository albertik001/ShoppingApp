package com.geektech.shoppingapp.presentation.ui.activity.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geektech.shoppingapp.databinding.ItemNotShopBinding
import com.geektech.shoppingapp.databinding.ItemShopBinding
import com.geektech.shoppingapp.presentation.models.ShopItemUI
import com.google.android.material.snackbar.Snackbar

class ShopAdapter(
    private val onClickItem: ((shopItemId: ShopItemUI) -> Unit?)? = null,
    private val onLongClickItem: ((shopItemId: Int) -> Unit?)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = listOf<ShopItemUI>()
    var list = listOf<ShopItemUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        try {
            when (viewType) {
                LAYOUT_TRUE -> {
                    return ShopViewHolder(
                        ItemShopBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
                LAYOUT_FALSE -> {
                    return NotShopViewHolder(
                        ItemNotShopBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
            }
        } catch (e: IllegalStateException) {
            Snackbar.make(parent, "An error has occurred, wait", Snackbar.LENGTH_SHORT).show()
        }
        throw IllegalArgumentException("Not found")
    }

    override fun getItemViewType(position: Int): Int {
        return when (_list[position].enable) {
            true -> LAYOUT_FALSE
            false -> LAYOUT_TRUE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            when (getItemViewType(position)) {
                LAYOUT_TRUE -> (holder as ShopViewHolder).onBind(_list[position])
                LAYOUT_FALSE -> (holder as NotShopViewHolder).onBind(_list[position])
            }
        } catch (e: IllegalStateException) {
            Snackbar.make(holder.itemView, "An error has occurred, wait", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount() = _list.size

    fun setLists(list: List<ShopItemUI>) {
        val callback = ShopListDiffCallback(this._list, list)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        this._list = list
        this.list = _list
    }

    inner class ShopViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(shopItem: ShopItemUI) {
            binding.tvCount.text = shopItem.count.toString()
            binding.tvName.text = shopItem.name.toString()

            binding.root.setOnLongClickListener {
                onClickItem?.invoke(shopItem)
                return@setOnLongClickListener true
            }

            binding.root.setOnClickListener {
                onLongClickItem?.invoke(shopItem.id)
            }
        }
    }

    inner class NotShopViewHolder(private val binding: ItemNotShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(shopItem: ShopItemUI) {
            binding.tvCount.text = shopItem.count.toString()
            binding.tvName.text = shopItem.name.toString()

            binding.root.setOnLongClickListener {
                onClickItem?.invoke(shopItem)
                return@setOnLongClickListener true
            }

            binding.root.setOnClickListener {
                onLongClickItem?.invoke(shopItem.id)
            }
        }
    }

    companion object {
        private const val LAYOUT_TRUE = 0
        private const val LAYOUT_FALSE = 1
    }
}
