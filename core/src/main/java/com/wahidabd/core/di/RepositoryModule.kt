package com.wahidabd.core.di

import com.wahidabd.core.data.MovieRepository
import com.wahidabd.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(repo: MovieRepository): IMovieRepository

}