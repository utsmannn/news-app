package com.utsman.newsapp.domain.entity

import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

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
) {

    companion object {
        fun fromJsonString(json: String): News {
            val type = object : TypeToken<News>() {}.type
            return Gson().fromJson(json, type)
        }
    }

    fun toJsonString(): String {
        val type = object : TypeToken<News>() {}.type
        return Gson().toJson(this, type)
    }
}