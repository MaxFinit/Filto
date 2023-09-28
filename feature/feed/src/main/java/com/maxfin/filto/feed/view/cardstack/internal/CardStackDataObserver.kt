package com.maxfin.filto.feed.view.cardstack.internal

import androidx.recyclerview.widget.RecyclerView
import com.maxfin.filto.feed.view.cardstack.CardStackLayoutManager
import kotlin.math.min

class CardStackDataObserver(private val recyclerView: RecyclerView) :
    RecyclerView.AdapterDataObserver() {

    private val cardStackLayoutManager: CardStackLayoutManager
        get() {
            val manager = recyclerView.layoutManager
            if (manager is CardStackLayoutManager) {
                return manager
            }
            throw IllegalStateException("CardStackView must be set CardStackLayoutManager.")
        }


    override fun onChanged() {
        cardStackLayoutManager.topPosition = 0
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        // Do nothing
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        // Do nothing
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        // Do nothing
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        val topPosition: Int = cardStackLayoutManager.topPosition
        if (cardStackLayoutManager.itemCount == 0) {
            cardStackLayoutManager.topPosition = 0
        } else if (positionStart < topPosition) {
            val diff = topPosition - positionStart
            cardStackLayoutManager.topPosition = min(topPosition - diff, cardStackLayoutManager.itemCount - 1)
        }
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        cardStackLayoutManager.removeAllViews()
    }


}