package com.rizan.newsapp.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rizan.newsapp.data.Repository
import com.rizan.newsapp.data.remote.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryViewModel (private val repository: Repository) : ViewModel()  {
    val getCategory: Flow<PagingData<Category>> =
        repository.getCategory().cachedIn(viewModelScope)
}