package com.example.suitmediatest.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.example.suitmediatest.data.paging.AccountPagingSource
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.retrofit.ApiService

class AccountRepository(private  val apiService: ApiService) {
    fun getStory(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                AccountPagingSource(apiService)
            }
        ).liveData

    }
}