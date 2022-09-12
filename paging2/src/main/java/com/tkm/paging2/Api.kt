package com.tkm.paging2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("article/list/{page}/json")
    fun articleList(@Path("page") page: Int, @Query("page_size") pageSize: Int): Call<ArticleListResponse>
}