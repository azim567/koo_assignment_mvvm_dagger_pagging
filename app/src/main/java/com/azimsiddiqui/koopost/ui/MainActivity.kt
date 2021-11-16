package com.azimsiddiqui.koopost.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azimsiddiqui.koopost.R
import com.azimsiddiqui.koopost.data.model.Data
import com.azimsiddiqui.koopost.databinding.ActivityMainBinding
import com.azimsiddiqui.koopost.helper.Constants.EXTRA_POST_DATA
import com.azimsiddiqui.koopost.ui.adapter.PostItemClickListener
import com.azimsiddiqui.koopost.ui.adapter.PostListAdapter
import com.azimsiddiqui.koopost.ui.adapter.PostLoadStateAdapter
import com.azimsiddiqui.koopost.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PostItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()
    private lateinit var postListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postListAdapter = PostListAdapter(this)
        initRecyclerView()
        lifecycleScope.launch {
            viewModel.posts.collectLatest { pagedData ->
                postListAdapter.submitData(pagedData)
            }
        }
    }

    private fun initRecyclerView() {

        binding.rvPostList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            hasFixedSize()
            adapter = postListAdapter.withLoadStateHeaderAndFooter(
                header = PostLoadStateAdapter { postListAdapter.retry() },
                footer = PostLoadStateAdapter { postListAdapter.retry() }
            )

        }
    }

    override fun postOnClick(post: Data) {
        val intent = Intent(this, PostDetailActivity::class.java)
         intent.putExtra(EXTRA_POST_DATA,post)
        startActivity(intent)
    }

}