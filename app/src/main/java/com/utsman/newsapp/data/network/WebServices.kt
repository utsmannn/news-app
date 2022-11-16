package com.utsman.newsapp.data.network

import com.utsman.newsapp.data.entity.NewsResponse
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET(EndPoint.TOP_HEADLINE)
    suspend fun getTopHeadline(
        @Query(QueryParam.COUNTRY) country: String = "id",
        @Query(QueryParam.API_KEY) apiKey: String = "9f760f53bc054697a8a10766019b44ff",
        @Query(QueryParam.CATEGORY) category: String
    ) : Response<NewsResponse>

    object EndPoint {
        const val TOP_HEADLINE = "/v2/top-headlines"
    }

    object QueryParam {
        const val COUNTRY = "country"
        const val CATEGORY = "category"
        const val API_KEY = "apiKey"
    }

    companion object {
        private const val BASE_URL = "https://newsapiiii.org/"

        private fun create(): WebServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebServices::class.java)
        }

        fun modules(): Module {
            return module {
                single { create() }
            }
        }
    }
}