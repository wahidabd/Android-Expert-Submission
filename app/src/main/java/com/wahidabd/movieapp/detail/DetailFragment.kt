package com.wahidabd.movieapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.Movie
import com.wahidabd.core.utils.Constant
import com.wahidabd.core.utils.setProgress
import com.wahidabd.core.utils.showToast
import com.wahidabd.movieapp.MainViewModel
import com.wahidabd.movieapp.R
import com.wahidabd.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    private var status: Boolean = false
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        checkFavorite()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imgBack?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.imgFavorite?.setOnClickListener { setFavorite() }

        viewModel.getDetail(args.id).observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Loading -> {
                    binding?.progress?.setProgress(true)
                }
                is Resource.Error -> {
                    binding?.progress?.setProgress(false)
                    showToast(requireContext(), res.error)
                }
                is Resource.Success -> {
                    binding?.progress?.setProgress(false)
                    movie = res.data
                    setView(res.data)
                }

            }
        }
    }

    private fun setView(data: Movie) {
        binding?.apply {
            tvTitle.text = data.title
            tvReleaseDate.text = data.release_date
            tvDesc.text = data.overview

            imgItemPhoto.load(Constant.IMAGE_URL + data.poster_path)
            imgItemPreview.load(Constant.IMAGE_URL + data.backdrop_path)
        }
    }

    private fun checkFavorite() {
        viewModel.checkFavorite(args.id).observe(viewLifecycleOwner) { res ->
            status = res != null
            if (status) {
                binding?.imgFavorite?.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
                )
            } else {
                binding?.imgFavorite?.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_un_favorite)
                )
            }
        }
    }

    private fun setFavorite() {
        viewModel.setFavorite(movie, !status)
        checkFavorite()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}