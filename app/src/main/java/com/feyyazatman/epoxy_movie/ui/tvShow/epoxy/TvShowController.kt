package com.feyyazatman.epoxy_movie.ui.tvShow.epoxy

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.feyyazatman.epoxy_movie.data.model.TvShow
import com.feyyazatman.epoxy_movie.ui.movie.epoxy.headerTitle
import java.util.concurrent.CopyOnWriteArrayList

class TvShowController : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private val _tvShowPopular: CopyOnWriteArrayList<TvShow?> = CopyOnWriteArrayList()

    override fun buildModels() {
        if (_tvShowPopular.isNotEmpty()) {
            addHeaderTvShow(_tvShowPopular[0])
            addTitle("Popular")
            for (listItem in _tvShowPopular) {
                addCard(listItem)
            }
        }
    }

    fun submitTvShow(items: Collection<TvShow?>) {
        _tvShowPopular.clear()
        _tvShowPopular.addAll(items)
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

    private fun addCard(tvShow: TvShow?) {
        tvShow?.let {
            tvShowCard {
                id(tvShow.id)
                tvShow(tvShow)
            }
        }
    }

    private fun addHeaderTvShow(tvShow: TvShow?) {
        tvShow?.let {
            headerTvShow {
                id(tvShow.id)
                tvShowPopular(tvShow)
            }
        }
    }
}