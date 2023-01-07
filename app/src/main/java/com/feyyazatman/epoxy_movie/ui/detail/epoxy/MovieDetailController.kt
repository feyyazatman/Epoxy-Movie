package com.feyyazatman.epoxy_movie.ui.detail.epoxy

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.feyyazatman.epoxy_movie.data.model.Cast
import com.feyyazatman.epoxy_movie.data.model.MovieDetail
import com.feyyazatman.epoxy_movie.ui.movie.epoxy.MoviePosterItemModel_
import com.feyyazatman.epoxy_movie.ui.movie.epoxy.headerTitle
import java.util.concurrent.CopyOnWriteArrayList

class MovieDetailController : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private val _movieCast: CopyOnWriteArrayList<Cast?> = CopyOnWriteArrayList()
    private var movieDetail: MovieDetail? = null

    override fun buildModels() {
        if (movieDetail != null) {
            buildHeader(movieDetail)
        }
        if (_movieCast.isNotEmpty()) {
            val models = _movieCast.map { cast ->
                MoviePosterItemModel_()
                    .id(cast?.id)
                    .imageUrl(cast?.profilePath)
                    .title(cast?.originalName)
            }
            addTitle("Cast")
            carousel {
                id("castCarousel")
                numViewsToShowOnScreen(4F)
                models(models)
            }
        }
    }

    fun submitMovieDetail(item: MovieDetail) {
        movieDetail = item
        requestModelBuild()
    }

    fun submitCast(items: Collection<Cast?>) {
        _movieCast.clear()
        _movieCast.addAll(items)
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

    private fun buildHeader(movie: MovieDetail?) {
        movie?.let {
            movieDetail{
                id(movie.id)
                movieDetailItem(movie)
            }
        }
    }




}