package com.utsman.newsapp

import android.app.Application
import com.utsman.newsapp.di.KoinProvider

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinProvider.initKoin(this)
    }
}