package com.maxfin.filto.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxfin.filto.feed.databinding.FragmentFeedBinding
import com.maxfin.filto.feed.view.cardstack.CardStackLayoutManager
import com.maxfin.filto.feed.view.cardstack.CardStackListener
import com.maxfin.filto.feed.view.cardstack.Direction
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed), CardStackListener {

    private val binding: FragmentFeedBinding by viewBinding()

    private val viewModel: FeedViewModel by viewModels()

    private val feedCardAdapter = ItemAdapter<FeedCardHolder>()
    private val feedCardFastAdapter = FastAdapter.with(feedCardAdapter)

    private lateinit var feedCardListManager: CardStackLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFeedCardList()
    }

    private fun initFeedCardList() {
        feedCardListManager = CardStackLayoutManager(requireContext(), this).apply {
            setTranslationInterval(8f)
            setMaxDegree(-80f)
            setCanScrollVertical(false)
            setVisibleCount(1)
        }

        binding.feedCard.layoutManager = feedCardListManager
        binding.feedCard.adapter = feedCardFastAdapter



        observeMeal()
    }

    private fun observeMeal() {
        viewModel.mealList.observe(viewLifecycleOwner) {
            it.forEach { meal ->
                feedCardAdapter.add(FeedCardHolder(meal))
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        viewModel.getMeal()
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

}