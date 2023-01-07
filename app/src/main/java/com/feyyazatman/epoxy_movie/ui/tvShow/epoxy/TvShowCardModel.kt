package com.feyyazatman.epoxy_movie.ui.tvShow.epoxy

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.data.model.TvShow
import com.feyyazatman.epoxy_movie.databinding.ItemTvShowCardLayoutBinding
import com.feyyazatman.epoxy_movie.ui.utils.loadImage

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_tv_show_card_layout)
abstract class TvShowCardModel : EpoxyModelWithHolder<TvShowCardModel.ViewHolder>() {

    @JvmField
    @EpoxyAttribute
    var tvShow: TvShow? = null


    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            ivTvShow.loadImage(tvShow?.posterPath)
            tvShowTitle.text = tvShow?.name
            rbTvShow.rating = ((tvShow?.voteAverage?.div(2))?.toFloat() ?: 0.0) as Float
            tvVotes.text = tvShow?.voteCount.toString()
        }
    }


    class ViewHolder : EpoxyHolder() {
        lateinit var binding : ItemTvShowCardLayoutBinding

        override fun bindView(itemView: View) {
            binding = ItemTvShowCardLayoutBinding.bind(itemView)
        }
    }
}