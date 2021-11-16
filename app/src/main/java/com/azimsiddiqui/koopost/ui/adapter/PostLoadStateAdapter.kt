package com.azimsiddiqui.koopost.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azimsiddiqui.koopost.databinding.ItemLoadingStateBinding
import com.azimsiddiqui.koopost.util.visible

class PostLoadStateAdapter(
private val retry: () -> Unit
) : LoadStateAdapter<PostLoadStateAdapter.PostLoadStateViewHolder>() {

    inner class PostLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }
            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.buttonRetry.visible(loadState is LoadState.Error)
            binding.textViewError.visible(loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(holder: PostLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PostLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}