package com.tkm.paging3_basic

private const val ITEMS_PER_PAGE = 50

class ArticleRepository {
    fun articlePagingSource() = ArticlePagingSource()
}