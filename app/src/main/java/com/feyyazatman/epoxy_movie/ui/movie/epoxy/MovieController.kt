package com.feyyazatman.epoxy_movie.ui.movie.epoxy

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.feyyazatman.epoxy_movie.data.model.Movie
import java.util.concurrent.CopyOnWriteArrayList

class MovieController(private val buttonOnClickListener: ButtonOnClickListener) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private val _popularMovies: CopyOnWriteArrayList<Movie?> = CopyOnWriteArrayList()
    private val _upcomingMovies: CopyOnWriteArrayList<Movie?> = CopyOnWriteArrayList()
    private val _nowPlayingMovies: CopyOnWriteArrayList<Movie?> = CopyOnWriteArrayList()

    override fun buildModels() {

        if (_popularMovies.isNotEmpty()) {
            addHeaderMovie(_popularMovies[0])
            addTitle("Popular")

            val models = _popularMovies.map { movie ->
                MoviePosterItemModel_()
                    .id(movie?.id)
                    .title(movie?.title)
                    .imageUrl(movie?.posterPath)
                    .movieId(movie?.id.toString())
                    .buttonOnClick(buttonOnClickListener)
            }
            carousel {
                id("carousel")
                numViewsToShowOnScreen(3F)
                models(models)
            }
        }
        if (_nowPlayingMovies.isNotEmpty()) {
            addTitle("In Theaters")
            val models = _nowPlayingMovies.map { movie ->
                MoviePosterItemModel_()
                    .id(movie?.id)
                    .title(movie?.title)
                    .imageUrl(movie?.posterPath)
                    .movieId(movie?.id.toString())
                    .buttonOnClick(buttonOnClickListener)
            }
            carousel {
                id("carousel2")
                numViewsToShowOnScreen(3F)
                models(models)
            }
        }
        if (_upcomingMovies.isNotEmpty()) {
            addTitle("Upcoming")
            val models = _upcomingMovies.map { movie ->
                MoviePosterItemModel_()
                    .id(movie?.id)
                    .title(movie?.title)
                    .imageUrl(movie?.posterPath)
                    .movieId(movie?.id.toString())
                    .buttonOnClick(buttonOnClickListener)
            }
            carousel {
                id("carousel3")
                numViewsToShowOnScreen(3F)
                models(models)
            }
        }
    }

    fun submitPopularMovies(items: Collection<Movie?>) {
        _popularMovies.clear()
        _popularMovies.addAll(items)
        requestModelBuild()
    }

    fun submitUpcomingMovies(items: Collection<Movie?>) {
        _upcomingMovies.clear()
        _upcomingMovies.addAll(items)
        requestModelBuild()
    }

    fun submitNowPlayingMovies(items: Collection<Movie?>) {
        _nowPlayingMovies.clear()
        _nowPlayingMovies.addAll(items)
        requestModelBuild()
    }

    private fun addTitle(title: String?) {
        title?.let {
            headerTitle {
                id(title)
                title(title)
            }
        }
    }

    private fun addHeaderMovie(movie: Movie?) {
        movie?.let {
            headerMovie {
                id(movie.id)
                movieTitle(movie.title)
                imageUrl(movie.posterPath)
                movieVoteCount(movie.voteCount.toString())
                movieVoteAverage(movie.voteAverage)
            }
        }
    }
}