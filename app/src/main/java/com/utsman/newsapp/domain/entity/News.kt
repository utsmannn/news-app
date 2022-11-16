package com.utsman.newsapp.domain.entity

data class News(
    val id: String = "",
    val source: String = "",
    val author: String = "",
    val title: String = "",
    val description: String,
    val url: String = "",
    val imageUrl: String = "",
    val publishedAt: String = "",
    val content: String = ""
)