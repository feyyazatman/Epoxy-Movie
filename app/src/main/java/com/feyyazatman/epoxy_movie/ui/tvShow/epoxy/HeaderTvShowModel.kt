package com.feyyazatman.epoxy_movie.ui.tvShow.epoxy

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.data.model.TvShow
import com.feyyazatman.epoxy_movie.databinding.ItemHeaderTvShowLayoutBinding
import com.feyyazatman.epoxy_movie.ui.utils.loadImage


@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_header_tv_show_layout)
abstract class HeaderTvShowModel : EpoxyModelWithHolder<HeaderTvShowModel.ViewHolder>(){


    @JvmField
    @EpoxyAttribute
    var tvShowPopular: TvShow? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            ivTvShowHeader.loadImage(tvShowPopular?.posterPath)
            tvMovieTitle.text = tvShowPopular?.name
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var binding : ItemHeaderTvShowLayoutBinding

        override fun bindView(itemView: View) {
            binding = ItemHeaderTvShowLayoutBinding.bind(itemView)
        }

    }

}