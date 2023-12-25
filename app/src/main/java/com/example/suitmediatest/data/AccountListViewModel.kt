package com.example.suitmediatest.data

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatest.data.repo.AccountRepository
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.response.UserAccountResponse
import com.example.suitmediatest.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class AccountListViewModel(private val repository: AccountRepository) : ViewModel() {
    private val _listAccount = MutableLiveData<ArrayList<DataItem?>>()
    val listAccount: LiveData<ArrayList<DataItem?>> = _listAccount

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val listPaging: LiveData<PagingData<DataItem>> =
        repository.getStory().cachedIn(viewModelScope)

}
