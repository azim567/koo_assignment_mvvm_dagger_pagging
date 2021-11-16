package com.azimsiddiqui.koopost.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azimsiddiqui.koopost.data.model.Data
import com.azimsiddiqui.koopost.databinding.ItemRowQuestionBinding

//class PostListAdapter(private var listener: PostItemClickListener):
//    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
//
//    private  var postList=ArrayList<Data>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.PostViewHolder {
//        val layoutInflater= LayoutInflater.from(parent.context).inflate(R.layout.item_row_question,parent,false)
//        return PostViewHolder(layoutInflater)
//    }
//
//
//    inner class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//        fun bind(post: Data) {
//
//            itemView.tv_post_title.text = post.title
//            itemView.tv_post_content.text=post.body
//            itemView.parent_layout.setOnClickListener {
//                listener.postOnClick(post)
//            }
//        }
//
//
//    }
//
//    override fun onBindViewHolder(holder: PostListAdapter.PostViewHolder, position: Int) {
//        holder.bind(postList[position])
//    }
//
//    override fun getItemCount(): Int {
//       return postList.size
//    }
//
//    fun setData(list:List<Data>){
//        postList.clear()
//        postList.addAll(list)
//        notifyDataSetChanged()
//    }
//}


class PostListAdapter(val listener: PostItemClickListener) :
    PagingDataAdapter<Data, PostListAdapter.PostViewHolder>(PostComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        return PostViewHolder(
            ItemRowQuestionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPostItem(it)}
    }

    inner class PostViewHolder(private val binding: ItemRowQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPostItem(item: Data) = with(binding) {
            tvPostTitle.text=item.title
            tvPostContent.text=item.body
            root.setOnClickListener {
                listener.postOnClick(item)
            }
        }


    }

    object PostComparator : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}