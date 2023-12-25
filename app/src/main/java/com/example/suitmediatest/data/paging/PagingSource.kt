package com.example.suitmediatest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.retrofit.ApiConfig
import com.example.suitmediatest.data.retrofit.ApiService

class AccountPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
//            val responseData = database.listStoryDao().getAllStory()
//            return responseData.load(params)
            val responseData = apiService.getAllUsers( position, params.loadSize)
            LoadResult.Page(
                data = responseData.listData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.listData.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}