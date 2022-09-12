package com.tkm.paging2

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class ArticleListDataSourceFactory : DataSource.Factory<Int, Article>() {
    override fun create(): DataSource<Int, Article> {
        return ArticleDataSource()
    }
}