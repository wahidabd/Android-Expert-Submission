package com.wahidabd.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.core.ui.MovieAdapter
import com.wahidabd.core.utils.showToast
import com.wahidabd.favorite.databinding.FragmentFavoriteBinding
import com.wahidabd.favorite.di.ViewModelFactory
import com.wahidabd.favorite.di.inject
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels {factory}

    private lateinit var mAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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

        viewModel.favorites().observe(viewLifecycleOwner) { res ->
            if (res.isNotEmpty()){
                mAdapter.setData = res
            }else{
                showToast("Favorite is empty")
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

}