package com.cm2929.finalproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm2929.finalproject.adapters.PostAdapter
import com.cm2929.finalproject.databinding.ActivityMainBinding
import com.cm2929.finalproject.models.Post
import com.cm2929.finalproject.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private var currentPostId = 1 // Track the next post ID to fetch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postAdapter = PostAdapter(mutableListOf()) // Initialize with an empty list
        binding.recyclerViewPosts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPosts.adapter = postAdapter

        binding.buttonFetchPost.setOnClickListener { fetchNextPost() } // Set button click listener
    }

    private fun fetchNextPost() {
        RetrofitInstance.api.getPost(currentPostId).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    response.body()?.let { post ->
                        val postWithImage = post.copy(
                            imageUrl = "https://picsum.photos/200/200?random=${System.currentTimeMillis()}"
                        )
                        postAdapter.addPost(postWithImage) // Add the fetched post to the adapter
                        currentPostId++ // Increment the post ID for the next fetch
                    }
                } else {
                    Toast.makeText(this@MainActivity, "No more posts available", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch post", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
