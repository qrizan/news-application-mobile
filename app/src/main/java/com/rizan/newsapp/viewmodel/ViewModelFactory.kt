package com.rizan.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizan.newsapp.data.Repository
import com.rizan.newsapp.di.Injection
import com.rizan.newsapp.ui.categories.CategoryViewModel
import com.rizan.newsapp.ui.detail.DetailViewModel
import com.rizan.newsapp.ui.home.HomeViewModel

class ViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepository()
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}