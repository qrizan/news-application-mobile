package com.rizan.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import com.rizan.newsapp.data.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {
    fun getDetailArticle(slug: String) = repository.getDetailArticle(slug)

    fun getDetailCategory(slug: String) = repository.getDetailCategory(slug)
}