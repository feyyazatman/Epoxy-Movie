package com.feyyazatman.epoxy_movie.ui.detail.epoxy

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.data.model.MovieDetail
import com.feyyazatman.epoxy_movie.databinding.ItemMovieDetailLayoutBinding
import com.feyyazatman.epoxy_movie.ui.utils.loadImage


@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_movie_detail_layout)
abstract class MovieDetailModel : EpoxyModelWithHolder<MovieDetailModel.ViewHolder>() {

    @JvmField
    @EpoxyAttribute
    var movieDetailItem : MovieDetail? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            ivMovie.loadImage(movieDetailItem?.posterPath)
            tvMovieName.text = movieDetailItem?.title
            movieDetailItem?.genres?.forEach {
                tvMovieCategories.text = it?.name
            }
            rbMovie.rating = ((movieDetailItem?.voteAverage?.div(2))?.toFloat() ?: 0.0) as Float
            tvVotes.text = movieDetailItem?.voteCount.toString()
            tvCalendar.text = movieDetailItem?.releaseDate
            tvTime.text = movieDetailItem?.runtime.toString() + " min"
            movieDetailItem?.spokenLanguages?.forEach {
                tvLang.text =  it?.englishName
            }
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var binding: ItemMovieDetailLayoutBinding

        override fun bindView(itemView: View) {
            binding = ItemMovieDetailLayoutBinding.bind(itemView)
        }

    }
}