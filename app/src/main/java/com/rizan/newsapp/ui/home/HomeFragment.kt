package com.rizan.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizan.newsapp.data.remote.model.Article
import com.rizan.newsapp.data.remote.model.Category
import com.rizan.newsapp.databinding.FragmentHomeBinding
import com.rizan.newsapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance()
    }

    private val categoryAdapter by lazy {
        CategoryAdapter { category ->
            detailCategory(category)
        }
    }

    private val articleAdapter by lazy {
        ArticleAdapter { article ->
            detailArticle(article)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setObserver()
    }

    private fun detailArticle(article: Article) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailArticleFragment(slug = article.slug)
        findNavController().navigate(action)
    }

    private fun detailCategory(category: Category) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailCategoryFragment(
            slug = category.slug,
            title = category.name
        )
        findNavController().navigate(action)
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.category.collectLatest {
                categoryAdapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.article.collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    private fun setUi() = with(binding) {
        rvCategory.apply {
            adapter = categoryAdapter
            setHasFixedSize(false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        rvArticle.apply {
            adapter = articleAdapter
            setHasFixedSize(false)
            layoutManager =
                LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}