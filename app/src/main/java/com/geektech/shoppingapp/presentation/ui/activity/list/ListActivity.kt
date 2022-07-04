package com.geektech.shoppingapp.presentation.ui.activity.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.shoppingapp.R
import com.geektech.shoppingapp.databinding.ActivityListBinding
import com.geektech.shoppingapp.domain.entity.ShopItemModel
import com.geektech.shoppingapp.presentation.models.ShopItemUI
import com.geektech.shoppingapp.presentation.models.toDomain
import com.geektech.shoppingapp.presentation.ui.activity.detailActivity.DetailActivity
import com.geektech.shoppingapp.presentation.ui.activity.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListActivity : AppCompatActivity(R.layout.activity_list) {

    private val binding: ActivityListBinding by viewBinding(ActivityListBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private val adapter = ShopAdapter(this::onClickItem, this::onLongClickItem)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initViewModel()
        initListener()
    }

    private fun checkItemCount() {

    }

    private fun initListener() {
        binding.fabAddItem.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            resultLauncher.launch(intent)
        }

    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val name = intent?.getStringExtra("detailName")
            val count = intent?.getStringExtra("detailCount")
            viewModel.addShopItem(
                ShopItemModel(
                    name, count?.toInt(),
                    false
                )
            )
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getShopList().observe(this@ListActivity) {
                adapter.setLists(it)
                viewModel.checkItemCount(it.size, {
                    binding.layoutNoCount.root.isVisible = true
                },
                    { binding.layoutNoCount.root.isVisible = false })
            }
        }
    }

    private fun initAdapter() {
        binding.listRv.adapter = adapter
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.list[viewHolder.absoluteAdapterPosition]
                viewModel.removeShopItem(item.toDomain())
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.listRv)
    }

    private fun onClickItem(shopItem: ShopItemUI) {
        viewModel.editShopItem(shopItem.toDomain())
        initViewModel()
    }

    private fun onLongClickItem(shopItemId: Int) {
        viewModel.viewModelScope.launch {
            Toast.makeText(
                this@ListActivity,
                viewModel.getShopItem(shopItemId).toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}