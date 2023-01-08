package com.wahidabd.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahidabd.core.domain.model.Movie
import com.wahidabd.core.domain.usecase.MovieUseCase

class FavoriteViewModel (private val useCase: MovieUseCase) : ViewModel() {

    fun favorites(): LiveData<List<Movie>> = useCase.getFavorites().asLiveData()

}