package com.cm2929.finalproject.models

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String
)