package com.azimsiddiqui.koopost.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azimsiddiqui.koopost.R
import com.azimsiddiqui.koopost.data.model.Data
import com.azimsiddiqui.koopost.databinding.ActivityPostDetailBinding
import com.azimsiddiqui.koopost.helper.Constants.EXTRA_POST_DATA
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.extras?.get(EXTRA_POST_DATA) as Data
        post?.let { setupUI(it) }


    }

    private fun setupUI(data: Data) {
    with(binding){
        txtUserId.text=data.user_id.toString()
        tvPostTitle.text=data.title
        tvPostContent.text=data.body
    }

    }
}