package com.cm2929.finalproject.network

import retrofit2.http.GET
import com.cm2929.finalproject.models.Post
import retrofit2.Call

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}