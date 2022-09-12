package com.tkm.paging3

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidApi {
    @GET("article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int, @Query("page_size") pageSize: Int): ArticleListResponse
}