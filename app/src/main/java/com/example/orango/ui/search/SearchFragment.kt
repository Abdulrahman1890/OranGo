package com.example.orango.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.roomDB.entities.ProductEntity
import com.example.orango.R
import com.example.orango.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val productRecyclerViewAdapter by lazy {
        SearchAdapter(mutableListOf())
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view)

        populateSearchAdapter()

        binding.backFromSearch.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment2_to_homeFragment2)
        }

        binding.searchViewEditText.doOnTextChanged { text, start, before, count ->
            viewModel.search(text.toString())
        }


        viewModel.products.observe(viewLifecycleOwner){ products->
            productRecyclerViewAdapter.updateList(products)
        }

    }

    private fun populateSearchAdapter() {
        binding.servicesList.layoutManager = linearLayoutManager

        val adapter = productRecyclerViewAdapter
        binding.servicesList.adapter = adapter
    }

}