package com.maxfin.filto.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maxfin.filto.data.model.Meal
import com.maxfin.filto.feed.databinding.HolderFeedCardBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class FeedCardHolder(private val meal: Meal) :
    AbstractBindingItem<HolderFeedCardBinding>() {

    override val type: Int
        get() = R.id.id_feed_card_holder

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): HolderFeedCardBinding {
        return HolderFeedCardBinding.inflate(inflater, parent, false)
    }


    override fun bindView(binding: HolderFeedCardBinding, payloads: List<Any>) {
        binding.mealData = meal
        binding.executePendingBindings()
    }


}