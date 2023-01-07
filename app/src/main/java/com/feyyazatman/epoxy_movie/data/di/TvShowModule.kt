package com.feyyazatman.epoxy_movie.data.di

import com.feyyazatman.epoxy_movie.data.remote.api.ApiService
import com.feyyazatman.epoxy_movie.data.repository.tvShow.TvShowRepository
import com.feyyazatman.epoxy_movie.data.repository.tvShow.TvShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class TvShowModule {

    @Provides
    fun provideMovieRepository(apiService : ApiService) : TvShowRepository {
        return TvShowRepositoryImpl(apiService)
    }
}