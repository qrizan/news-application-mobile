package com.rizan.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rizan.newsapp.data.remote.model.Category
import com.rizan.newsapp.databinding.FragmentCategoryBinding
import com.rizan.newsapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CategoryViewModel> {
        ViewModelFactory.getInstance()
    }

    private val categoryAllAdapter by lazy {
        CategoryAllAdapter { category ->
            detailCategory(category)
        }
    }

    private fun detailCategory(category: Category) {
        val action = CategoryFragmentDirections.actionNavigationCategoriesToDetailCategoryFragment(
            slug = category.slug,
            title = category.name
        )
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setObserver()
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCategory.collectLatest {
                categoryAllAdapter.submitData(it)
            }
        }
    }

    private fun setUi() = with(binding) {
        rvCategory.apply {
            adapter = categoryAllAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}