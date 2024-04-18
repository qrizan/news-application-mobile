package com.rizan.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rizan.newsapp.data.Repository

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {
    val category =  repository.getCategory().cachedIn(viewModelScope)
    val article = repository.getArticle().cachedIn(viewModelScope)
}