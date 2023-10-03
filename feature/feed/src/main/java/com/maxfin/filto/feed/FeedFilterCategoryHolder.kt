package com.maxfin.filto.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.maxfin.filto.feed.databinding.HolderFeedFilterCategoryBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class FeedFilterCategoryHolder(private val key: String,private val name: String, @DrawableRes private val icon: Int) :
    AbstractBindingItem<HolderFeedFilterCategoryBinding>() {

    override var tag: Any? = key

    override val type: Int
        get() = R.id.id_feed_category_holder

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): HolderFeedFilterCategoryBinding {
        return HolderFeedFilterCategoryBinding.inflate(inflater, parent, false)
    }


    override fun bindView(binding: HolderFeedFilterCategoryBinding, payloads: List<Any>) {
        binding.isSelectedValue = isSelected
        binding.nameValue = name
        binding.iconValue = icon
        binding.executePendingBindings()
    }


}