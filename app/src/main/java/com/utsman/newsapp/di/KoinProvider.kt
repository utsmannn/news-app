package com.utsman.newsapp.di

import android.content.Context
import com.utsman.newsapp.data.network.WebServices
import com.utsman.newsapp.domain.repository.NewsRepository
import com.utsman.newsapp.view.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinProvider {

    fun initKoin(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                WebServices.modules(), // web services module
                NewsRepository.modules(), // repository module
                NewsViewModel.modules() // ViewModel module
            )
        }
    }
}