package com.example.suitmediatest.data.factory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediatest.data.AccountListViewModel
import com.example.suitmediatest.data.di.Injection
import com.example.suitmediatest.data.repo.AccountRepository

class ViewModelFactory private  constructor (private val historyRepository: AccountRepository) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AccountListViewModel::class.java) -> {
                AccountListViewModel(historyRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }


        companion object {
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(
                        Injection.provideRepository(context)
                    )
                }
        }
}





