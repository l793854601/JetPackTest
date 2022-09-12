package com.tkm.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

const val START_PAGE = 0
const val PAGE_SIZE = 20

object ArticleRepository {
    private val api = RetrofitUtil.api

    fun getPagingData(): Flow<PagingData<Article>> {
        val config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
            maxSize = Int.MAX_VALUE,
            prefetchDistance = 2,
            enablePlaceholders = true,
        )
        val dataSource = ArticleListDataSource(api)
        return Pager(
            config = config,
            pagingSourceFactory = { dataSource }
        ).flow
    }
}