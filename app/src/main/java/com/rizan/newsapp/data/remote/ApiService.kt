package com.rizan.newsapp.data.remote

import com.rizan.newsapp.data.remote.model.Article
import com.rizan.newsapp.data.remote.model.Category
import com.rizan.newsapp.data.remote.model.Paging
import com.rizan.newsapp.data.remote.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("public/categories")
    suspend fun getCategory(
        @Query("page") page: Int,
    ): Response<Paging<Category>>

    @GET("public/posts")
    suspend fun getArticle(
        @Query("page") page: Int
    ): Response<Paging<Article>>

    @GET("public/posts/{slug}")
    suspend fun getDetailArticle(
        @Path("slug") slug:String
    ):Response<Article>

    @GET("public/categories/{slug}")
    suspend fun getDetailCategory(
        @Path("slug") slug:String
    ):Response<Category>
}