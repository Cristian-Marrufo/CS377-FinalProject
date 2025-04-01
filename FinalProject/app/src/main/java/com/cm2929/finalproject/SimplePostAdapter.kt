package com.cm2929.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm2929.finalproject.databinding.ItemPostBinding

data class PostItem(val title: String, val description: String)

class SimplePostAdapter(private var posts: List<PostItem>) : RecyclerView.Adapter<SimplePostAdapter.PostViewHolder>() {
    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.textViewTitle.text = post.title
        holder.binding.textViewDescription.text = post.description
    }

    override fun getItemCount(): Int = posts.size

    fun updateData(newPosts: List<PostItem>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}