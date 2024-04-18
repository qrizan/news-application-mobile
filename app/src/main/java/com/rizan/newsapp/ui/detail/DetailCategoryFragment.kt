package com.rizan.newsapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizan.newsapp.R
import com.rizan.newsapp.data.Resource
import com.rizan.newsapp.data.remote.model.Article
import com.rizan.newsapp.databinding.FragmentDetailCategoryBinding
import com.rizan.newsapp.viewmodel.ViewModelFactory

class DetailCategoryFragment : Fragment() {
    private var _binding: FragmentDetailCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance()
    }

    private val articleByCategoryAdapter by lazy {
        ArticleByCategoryAdapter { article ->
            detailArticle(article)
        }
    }

    private fun detailArticle(article: Article) {
        val action =
            DetailCategoryFragmentDirections.actionDetailCategoryFragmentToDetailArticleFragment(
                slug = article.slug
            )
        findNavController().navigate(action)
    }

    private val args: DetailCategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setObserver()
    }

    private fun setUi() = with(binding) {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            title = args.title
        }
        rvArticle.apply {
            adapter = articleByCategoryAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setObserver() {
        viewModel.getDetailCategory(args.slug).observe(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val listArticle = resources.data.post
                    when (listArticle.isNotEmpty()) {
                        true -> {
                            articleByCategoryAdapter.submitList(listArticle)
                        }
                        else -> {
                            binding.tvEmpty.apply {
                                visibility = View.VISIBLE
                                text = "Article Empty"
                            }
                        }
                    }

                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), resources.error, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}