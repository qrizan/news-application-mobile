package com.rizan.newsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rizan.newsapp.data.datasource.ArticlePagingSource
import com.rizan.newsapp.data.datasource.CategoriesPagingSource
import com.rizan.newsapp.data.remote.ApiService
import com.rizan.newsapp.data.remote.model.Article
import com.rizan.newsapp.data.remote.model.Category
import kotlinx.coroutines.flow.Flow


class Repository private constructor(
    private val apiService: ApiService
) {
    companion object {
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository = instance ?: synchronized(this)
        {
            instance ?: Repository(apiService)
        }
    }

    private val pagingConfig = PagingConfig(
        pageSize = 5
    )

    fun getCategory(): Flow<PagingData<Category>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                CategoriesPagingSource(apiService)
            }

        ).flow
    }

    fun getArticle(): Flow<PagingData<Article>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                ArticlePagingSource(apiService)
            }

        ).flow
    }

    fun getDetailArticle(slug: String): LiveData<Resource<Article>> = liveData {
        emit(Resource.Loading)
        try {
            val response = apiService.getDetailArticle(slug)
            emit(Resource.Success(response.data))

        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getDetailCategory(slug: String): LiveData<Resource<Category>> = liveData {
        emit(Resource.Loading)
        try {
            val response = apiService.getDetailCategory(slug)
            emit(Resource.Success(response.data))

        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}