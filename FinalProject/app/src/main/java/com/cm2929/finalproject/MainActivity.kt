package com.cm2929.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm2929.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SimplePostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewPosts.layoutManager = LinearLayoutManager(this)

        val samplePosts = listOf(
            PostItem("Title 1", "Description for item 1"),
            PostItem("Title 2", "Description for item 2"),
            PostItem("Title 3", "Description for item 3")
        )

        adapter = SimplePostAdapter(samplePosts)
        binding.recyclerViewPosts.adapter = adapter
    }
}