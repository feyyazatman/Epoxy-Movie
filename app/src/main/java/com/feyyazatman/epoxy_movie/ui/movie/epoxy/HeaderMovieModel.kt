package com.feyyazatman.epoxy_movie.ui.movie.epoxy

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.databinding.ItemHeaderMovieLayoutBinding
import com.feyyazatman.epoxy_movie.ui.utils.loadImage

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_header_movie_layout)
abstract class HeaderMovieModel : EpoxyModelWithHolder<HeaderMovieModel.ViewHolder>() {

    @JvmField
    @EpoxyAttribute
    var movieTitle: String? = null

    @JvmField
    @EpoxyAttribute
    var imageUrl: String? = null

    @JvmField
    @EpoxyAttribute
    var movieVoteCount: String? = null

    @JvmField
    @EpoxyAttribute
    var movieVoteAverage: Double? = null


    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            ivMovieHeader.loadImage(imageUrl)
            tvMovieTitle.text = movieTitle
            rbMovie.rating = ((movieVoteAverage?.div(2))?.toFloat() ?: 0.0) as Float
            tvVotes.text = movieVoteCount
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var binding : ItemHeaderMovieLayoutBinding

        override fun bindView(itemView: View) {
            binding = ItemHeaderMovieLayoutBinding.bind(itemView)
        }
    }


}