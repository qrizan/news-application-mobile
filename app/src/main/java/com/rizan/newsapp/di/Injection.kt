package com.rizan.newsapp.di

import com.rizan.newsapp.data.Repository
import com.rizan.newsapp.data.remote.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}