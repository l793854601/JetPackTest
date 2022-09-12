package com.tkm.paging2

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleDataSource() : PageKeyedDataSource<Int, Article>() {

    companion object {
        const val START_PAGE = 0
        const val PAGE_SIZE = 20
    }

    //  首次加载
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        RetrofitUtil.api
            .articleList(START_PAGE, PAGE_SIZE)
            .enqueue(object : Callback<ArticleListResponse> {
                override fun onResponse(call: Call<ArticleListResponse>, response: Response<ArticleListResponse>) {
                    val pagedData = response.body()?.pagedData
                    val result = pagedData?.datas ?: listOf()
                    val nextPage = START_PAGE + 1
                    callback.onResult(result, null, nextPage)
                }

                override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {

                }
            })
    }

    //  加载前一页，基本用不到
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    //  加载下一页
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        RetrofitUtil.api
            .articleList(params.key, PAGE_SIZE)
            .enqueue(object : Callback<ArticleListResponse> {
                override fun onResponse(call: Call<ArticleListResponse>, response: Response<ArticleListResponse>) {
                    val pagedData = response.body()?.pagedData
                    val result = pagedData?.datas ?: listOf()
                    val nextPage = if (pagedData?.over == true) null else params.key + 1
                    callback.onResult(result, nextPage)
                }

                override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {

                }
            })
    }
}


