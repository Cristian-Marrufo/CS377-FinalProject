package com.cm2929.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm2929.finalproject.databinding.ItemPostBinding
import com.cm2929.finalproject.models.Post

data class PostItem(val title: String, val description: String)

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var onItemClickListener: ((Post) -> Unit)? = null

    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, clickListener: ((Post) -> Unit)?) {
            binding.textViewTitle.text = post.title
            binding.textViewBody.text = post.body
            binding.root.setOnClickListener { clickListener?.invoke(post) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], onItemClickListener)
    }

    override fun getItemCount(): Int = posts.size
}