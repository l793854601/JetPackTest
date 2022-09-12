package com.tkm.paging2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class ArticleViewModel : ViewModel() {
    val pagedListArticle by lazy {
        val config = PagedList.Config.Builder()
            //  设置控件占位
            .setEnablePlaceholders(false)
            //  每一页数量大小
            .setPageSize(ArticleDataSource.PAGE_SIZE)
            //  设置首次加载多少条数据
            .setInitialLoadSizeHint(ArticleDataSource.PAGE_SIZE)
            //  一共加载多少条数据
            .setMaxSize(Int.MAX_VALUE)
            //  距离底部还有几条数据时开始加载
            .setPrefetchDistance(2)
            .build()

        val data = LivePagedListBuilder(ArticleListDataSourceFactory(), config).build()
        data
    }

    fun refreshData() {
        pagedListArticle.value?.dataSource?.invalidate()
    }
}