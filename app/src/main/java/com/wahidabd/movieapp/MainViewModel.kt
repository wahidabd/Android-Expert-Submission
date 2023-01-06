package com.wahidabd.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.MovieModel
import com.wahidabd.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieModel>>> = useCase.getMovies().asLiveData()
    fun getDetail(id: Int): LiveData<Resource<MovieModel>> = useCase.getDetail(id).asLiveData()
    fun checkFavorite(id: Int): LiveData<MovieModel> = useCase.checkFavorite(id).asLiveData()
    fun setFavorite(movie: MovieModel, newState: Boolean) = useCase.setFavorite(movie, newState)

}