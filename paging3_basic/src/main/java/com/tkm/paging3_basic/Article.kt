package com.tkm.paging3_basic

import java.time.LocalDateTime

data class Article(
    val id: Int,
    val title: String?,
    val description: String?,
    val created: LocalDateTime?
)