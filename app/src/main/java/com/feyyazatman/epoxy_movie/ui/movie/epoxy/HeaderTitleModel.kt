package com.feyyazatman.epoxy_movie.ui.movie.epoxy

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.databinding.ItemHeaderTitleLayoutBinding

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_header_title_layout)
abstract class HeaderTitleModel : EpoxyModelWithHolder<HeaderTitleModel.ViewHolder>() {

    @JvmField
    @EpoxyAttribute
    var title: String? = null


    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.binding.tvHeaderTitle.text = title
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var binding : ItemHeaderTitleLayoutBinding

        override fun bindView(itemView: View) {
            binding = ItemHeaderTitleLayoutBinding.bind(itemView)
        }
    }
}