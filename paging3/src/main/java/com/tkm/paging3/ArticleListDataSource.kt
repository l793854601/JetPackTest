package com.tkm.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleListDataSource(private val api: WanAndroidApi) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val key = params.key ?: START_PAGE
            val loadSize = PAGE_SIZE

            val response = withContext(Dispatchers.IO) {
                api.articleList(key, loadSize)
            }

            response.pagedData?.let {
                val datas = it.datas ?: listOf()
                //  获取前一页数，不存在则为null
                val prevKey = if (key > 0) key - 1 else null
                //  获取后一页数，完结了则为null
                val nextKey = if (!it.over) key + 1 else null
                LoadResult.Page(datas, prevKey, nextKey)
            } ?: throw RuntimeException("数据为空")

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}























