package com.feyyazatman.epoxy_movie.ui.movie.epoxy

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.databinding.ItemMoviePosterLayoutBinding
import com.feyyazatman.epoxy_movie.ui.utils.loadImage

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_movie_poster_layout)
abstract class MoviePosterItemModel : EpoxyModelWithHolder<MoviePosterItemModel.ViewHolder>() {


    @JvmField
    @EpoxyAttribute
    var title: String? = null

    @JvmField
    @EpoxyAttribute
    var imageUrl: String? = null

    @JvmField
    @EpoxyAttribute
    var movieId: String? = null

    @JvmField
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var buttonOnClick: ButtonOnClickListener? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            tvMovieName.text = title
            ivMovie.loadImage(imageUrl)
            ivMovie.setOnClickListener {
                buttonOnClick?.onClick(movieId)
            }
        }

    }

    class ViewHolder : EpoxyHolder() {
        lateinit var binding : ItemMoviePosterLayoutBinding

        override fun bindView(itemView: View) {
            binding = ItemMoviePosterLayoutBinding.bind(itemView)
        }
    }
}

interface ButtonOnClickListener {
    fun onClick(id: String?)
}