package com.example.suitmediatest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatest.data.AccountListViewModel
import com.example.suitmediatest.data.adapter.AccountAdapter
import com.example.suitmediatest.data.adapter.LoadingAdapter
import com.example.suitmediatest.data.factory.ViewModelFactory
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.databinding.ActivitySecondPageBinding
import com.example.suitmediatest.databinding.ActivityThirdBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var accountAdapter: AccountAdapter
    private lateinit var accountListViewModel: AccountListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accountListViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[AccountListViewModel::class.java]

        accountAdapter = AccountAdapter()
        binding.rvList.adapter = accountAdapter

        getData()

        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true;
            getData()
            Toast.makeText(this, "List stories refreshed", Toast.LENGTH_SHORT).show()
        }


    }


    private fun getData(){
        accountListViewModel.listPaging.observe(this){pagingData ->
            lifecycleScope.launch {
                accountAdapter.loadStateFlow.collectLatest {
                    showListUser(it.refresh !is LoadState.Error)
                }
            }
            binding.swipeRefresh.isRefreshing = false;
            if (pagingData != null){
                Log.d("IS_ERROR", pagingData.toString())
                setList(pagingData)

            }
        }
    }
    private fun showListUser(state: Boolean){
        if (state){
            binding.swipeRefresh.visibility = View.VISIBLE
            binding.noDataText.visibility = View.GONE
        }else{
            binding.swipeRefresh.visibility = View.GONE
            binding.noDataText.visibility = View.VISIBLE
            }
        }
    private fun setList(story: PagingData<DataItem>){

        accountAdapter.submitData(lifecycle, story)

        accountAdapter.setOnItemClickCallback(object : AccountAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                setResult(Activity.RESULT_OK, intent.putExtra(TAG_PAGE_THIRD, "${data.firstName} ${data.lastName}"))
                finish()

            }
        })
    }

    companion object{
        const val TAG_PAGE_THIRD = "THIRD PAGE KEY"
    }


}