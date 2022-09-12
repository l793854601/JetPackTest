package com.tkm.paging3

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ArticleViewModel : ViewModel() {
    fun loadArticleList(): Flow<PagingData<Article>> {
        return ArticleRepository.getPagingData()
    }
}