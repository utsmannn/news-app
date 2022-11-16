package com.utsman.newsapp.data.entity


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?,
    @SerializedName("articles")
    var articles: List<Article?>?
) {
    data class Article(
        @SerializedName("source")
        var source: Source?,
        @SerializedName("author")
        var author: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("url")
        var url: String?,
        @SerializedName("urlToImage")
        var urlToImage: String?,
        @SerializedName("publishedAt")
        var publishedAt: String?,
        @SerializedName("content")
        var content: String?
    ) {
        data class Source(
            @SerializedName("id")
            var id: String?,
            @SerializedName("name")
            var name: String?
        )
    }
}