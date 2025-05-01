package com.cm2929.finalproject.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cm2929.finalproject.databinding.ItemPostBinding
import com.cm2929.finalproject.models.Post

class PostAdapter(private val posts: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var onItemClickListener: ((Post) -> Unit)? = null

    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }

    fun addPost(post: Post) {
        posts.add(post) // Add the new post to the list
        notifyItemInserted(posts.size - 1) // Notify the adapter of the new item
        Log.d("PostAdapter", "Image URL: $post.imageUrl")
    }

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, clickListener: ((Post) -> Unit)?) {
            binding.textViewTitle.text = post.title
            binding.textViewBody.text = post.body
            Glide.with(binding.root.context) // Use Glide to load the image
                .load(post.imageUrl)
                .into(binding.imageViewPost) // Ensure `imageViewPost` exists in your layout
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
