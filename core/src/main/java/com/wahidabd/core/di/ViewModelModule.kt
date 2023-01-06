package com.wahidabd.core.di

import com.wahidabd.core.domain.usecase.MovieInteractor
import com.wahidabd.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun provideMovieUseCase(interactor: MovieInteractor): MovieUseCase

}