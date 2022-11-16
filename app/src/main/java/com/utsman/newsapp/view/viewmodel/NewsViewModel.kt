package com.utsman.newsapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.utsman.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        newsRepository.sendErrorFromExceptionHandler(throwable)
    }

    private val safeScope = viewModelScope + errorHandler

    val topHeadline = newsRepository.topHeadline.asLiveData(
        context = safeScope.coroutineContext
    )

    fun getTopHeadline() = safeScope.launch {
        newsRepository.getTopHeadline()
    }

    companion object {
        fun modules(): Module {
            return module {
                viewModel { NewsViewModel(get()) }
            }
        }
    }
}