package com.geektech.shoppingapp.presentation.ui.activity.detailActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.shoppingapp.R
import com.geektech.shoppingapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private val binding: ActivityDetailBinding by viewBinding(ActivityDetailBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btnAddItem.setOnClickListener {
            val etName = binding.etName.text.toString()
            val etCount = binding.etCount.text.toString()
            val intent = Intent()
            if (etName.isNotEmpty() && etCount.isNotEmpty()) {
                intent.putExtra("detailName", etName)
                intent.putExtra("detailCount", etCount)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
