package com.maxfin.filto.feed

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxfin.filto.feed.databinding.FragmentFeedBinding
import com.maxfin.filto.feed.view.cardstack.CardStackLayoutManager
import com.maxfin.filto.feed.view.cardstack.CardStackListener
import com.maxfin.filto.feed.view.cardstack.Direction
import com.maxfin.filto.feed.view.cardstack.Duration
import com.maxfin.filto.feed.view.cardstack.SwipeAnimationSetting
import com.maxfin.filto.navigation.saveNavigate
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.select.getSelectExtension
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed), CardStackListener {

    private val binding: FragmentFeedBinding by viewBinding()

    private val viewModel: FeedViewModel by viewModels()

    private val feedCardAdapter = ItemAdapter<FeedCardHolder>()
    private val feedCardFastAdapter = FastAdapter.with(feedCardAdapter)
    private val feedCardSelectExtension = feedCardFastAdapter.getSelectExtension()

    private lateinit var feedCardListManager: CardStackLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(
            FeedFilterFragment.resultKey
        ) { key, bundle ->
            val category = bundle.getString(
                FeedFilterFragment.resultKeyCategory
            )
            viewModel.setCategory(category.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFeedCardList()
        initActions()
    }

    private fun initActions() {
        binding.filter.setOnClickListener {
            saveNavigate(com.maxfin.filto.navigation.R.id.action_feedFragment_to_feedFilterFragment)
        }

        binding.like.setOnClickListener {
            like()
        }

        binding.dislike.setOnClickListener {
            dislike()
        }

        binding.favorites.setOnClickListener {
            saveNavigate(com.maxfin.filto.navigation.R.id.action_feedFragment_to_favoritesFragment)
        }
    }

    private fun initFeedCardList() {
        feedCardSelectExtension.apply {
            isSelectable = true
            selectWithItemUpdate = true
            multiSelect = false
            allowDeselection = true
        }

        feedCardListManager = CardStackLayoutManager(requireContext(), this).apply {
            setTranslationInterval(8f)
            setVisibleCount(1)
        }
        binding.feedCard.layoutManager = feedCardListManager
        binding.feedCard.adapter = feedCardFastAdapter

        observeMeal()
    }

    private fun observeMeal() {
        viewModel.currentMeal.observe(viewLifecycleOwner) { meal ->
            feedCardAdapter.set(listOf(FeedCardHolder(meal)))
            binding.feedCard.rewind()
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        when (direction) {
            Direction.Left -> {
                dislike()
            }

            Direction.Right -> {
                like()
            }

            Direction.Top -> {}
            Direction.Bottom -> {}
            null -> {}
        }
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

    private fun like() {
        swipeAnimation(Direction.Right)

        viewModel.addToFavorites()
    }

    private fun dislike() {
        swipeAnimation(Direction.Left)
    }

    private fun swipeAnimation(direction: Direction) {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        feedCardListManager.setSwipeAnimationSetting(setting)
        binding.feedCard.swipe()
    }

}