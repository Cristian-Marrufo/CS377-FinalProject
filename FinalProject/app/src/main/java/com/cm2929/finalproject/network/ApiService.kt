package com.cm2929.finalproject.network

import retrofit2.http.GET
import retrofit2.http.Path
import com.cm2929.finalproject.models.Post
import retrofit2.Call

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post> // Fetch a single post by ID
}
