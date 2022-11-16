package com.utsman.newsapp.data

import com.utsman.newsapp.data.entity.NewsResponse
import com.utsman.newsapp.domain.entity.News

object Mapper {

    fun mapResponseToNews(response: NewsResponse?): List<News> {
        val newsResponse = response?.articles
        val mapper: (NewsResponse.Article?) -> News = { resp ->
            val newsId = "${resp?.title}-${resp?.publishedAt}"
            News(
                id = newsId,
                source = resp?.source?.name.orEmpty(),
                author = resp?.author.orEmpty(),
                title = resp?.title.orEmpty(),
                description = resp?.description.orEmpty(),
                url = resp?.url.orEmpty(),
                imageUrl = resp?.urlToImage.orEmpty(),
                publishedAt = resp?.publishedAt.orEmpty(),
                content = resp?.content.orEmpty()
            )
        }

        return newsResponse?.map(mapper).orEmpty()
    }
}