package com.example.suitmediatest.data.di

import android.content.Context
import com.example.suitmediatest.data.repo.AccountRepository
import com.example.suitmediatest.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): AccountRepository {
        val apiService = ApiConfig.getApiService()
        return AccountRepository( apiService)
    }
}