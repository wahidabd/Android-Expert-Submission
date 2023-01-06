package com.wahidabd.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahidabd.core.domain.model.MovieModel
import com.wahidabd.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteViewModel (private val useCase: MovieUseCase) : ViewModel() {

    fun favorites(): LiveData<List<MovieModel>> = useCase.getFavorites().asLiveData()

}