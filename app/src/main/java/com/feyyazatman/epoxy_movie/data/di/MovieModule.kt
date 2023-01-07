package com.feyyazatman.epoxy_movie.data.di

import com.feyyazatman.epoxy_movie.data.remote.api.ApiService
import com.feyyazatman.epoxy_movie.data.repository.movie.MovieRepository
import com.feyyazatman.epoxy_movie.data.repository.movie.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MovieModule {

    @Provides
    fun provideMovieRepository(apiService : ApiService) : MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}