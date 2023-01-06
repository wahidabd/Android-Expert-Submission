package com.wahidabd.movieapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.core.common.Resource
import com.wahidabd.core.ui.MovieAdapter
import com.wahidabd.core.utils.setProgress
import com.wahidabd.core.utils.showToast
import com.wahidabd.movieapp.MainViewModel
import com.wahidabd.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var mAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = MovieAdapter()
        binding.rvMovie.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        mAdapter.setOnItemClick { id ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            )
        }

        observable()
    }

    private fun observable(){
        viewModel.getMovies().observe(viewLifecycleOwner){ res ->
            when(res){
                is Resource.Loading -> {
                    binding.progressCircular.setProgress(true)
                }
                is Resource.Error -> {
                    binding.progressCircular.setProgress(false)
                    showToast(res.error)
                }
                is Resource.Success -> {
                    binding.progressCircular.setProgress(false)
                    mAdapter.setData = res.data
                }
            }
        }
    }

}