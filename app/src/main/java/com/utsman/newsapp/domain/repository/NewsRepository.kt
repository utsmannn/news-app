package com.utsman.newsapp.domain.repository

import com.utsman.newsapp.data.repository.NewsRepositoryImpl
import com.utsman.newsapp.domain.entity.News
import com.utsman.newsapp.event.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.Module
import org.koin.dsl.module

interface NewsRepository {
    val topHeadline: Flow<StateEvent<List<News>>>

    suspend fun getTopHeadline()

    suspend fun invalidate()

    fun sendErrorFromExceptionHandler(throwable: Throwable)

    companion object {
        fun modules(): Module {
            return module {
                single<NewsRepository> { NewsRepositoryImpl(get()) }
            }
        }
    }
}