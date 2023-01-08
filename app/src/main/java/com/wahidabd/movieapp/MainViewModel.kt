package com.wahidabd.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.Movie
import com.wahidabd.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> = useCase.getMovies().asLiveData()
    fun getDetail(id: Int): LiveData<Resource<Movie>> = useCase.getDetail(id).asLiveData()
    fun checkFavorite(id: Int): LiveData<Boolean> = useCase.checkFavorite(id).asLiveData()
    fun setFavorite(movie: Movie, newState: Boolean) = useCase.setFavorite(movie, newState)

}