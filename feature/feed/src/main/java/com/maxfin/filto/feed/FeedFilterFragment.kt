package com.maxfin.filto.feed

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maxfin.filto.data.model.Categories
import com.maxfin.filto.feed.databinding.FragmentFeedFilterSheetBinding
import com.maxfin.filto.ui.dp
import com.maxfin.filto.ui.recycler.Spacing
import com.maxfin.filto.ui.recycler.SpacingItemDecoration
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.select.getSelectExtension


class FeedFilterFragment : BottomSheetDialogFragment(R.layout.fragment_feed_filter_sheet) {

    companion object {
        const val resultKey = "FeedFilterResult"
        const val resultKeyCategory = "FeedFilterCategory"
    }

    private val binding: FragmentFeedFilterSheetBinding by viewBinding()

    private val categoriesAdapter = ItemAdapter<FeedFilterCategoryHolder>()
    private val categoriesFastAdapter = FastAdapter.with(categoriesAdapter)
    private val categoriesSelectExtension = categoriesFastAdapter.getSelectExtension()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFeedCardList()

        binding.filterSearch.setOnClickListener {
            val selectedCategory = if (categoriesSelectExtension.selectedItems.isEmpty()){
                ""
            }else{
                categoriesSelectExtension.selectedItems.first().tag.toString()
            }

            setFragmentResult(resultKey, bundleOf(resultKeyCategory to selectedCategory))
            findNavController().popBackStack()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            (view?.parent as ViewGroup).background =
                ColorDrawable(Color.TRANSPARENT)
        }

        return dialog
    }


    private fun initFeedCardList() {
        categoriesSelectExtension.apply {
            isSelectable = true
            selectWithItemUpdate = true
            multiSelect = false
            allowDeselection = true
        }

        binding.categoryList.adapter = categoriesFastAdapter
        binding.categoryList.addItemDecoration(SpacingItemDecoration(Spacing(vertical = 22.dp)))

        val categories = mutableListOf<FeedFilterCategoryHolder>()

        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Seafood.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_seafood),
                icon = R.drawable.ic_category_seafood
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Chicken.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_chicken),
                icon = R.drawable.ic_category_chicken
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Beef.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_beef),
                icon = R.drawable.ic_category_beef
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Vegetarian.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_vegetarian),
                icon = R.drawable.ic_category_vegetarian
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Starter.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_starter),
                icon = R.drawable.ic_category_starter
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Starter.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_salads),
                icon = R.drawable.ic_category_salads
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Side.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_side),
                icon = R.drawable.ic_category_side
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Pasta.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_pasta),
                icon = R.drawable.ic_category_pasta
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Breakfast.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_breakfast),
                icon = R.drawable.ic_category_breakfast
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Dessert.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_desserts),
                icon = R.drawable.ic_category_desserts
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Vegan.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_vegan),
                icon = R.drawable.ic_category_vegetarian
            )
        )
        categories.add(
            FeedFilterCategoryHolder(
                key = Categories.Miscellaneous.name,
                name = getString(com.maxfin.filto.ui.R.string.feed_filter_category_miscellaneous),
                icon = R.drawable.ic_category_miscellaneous
            )
        )

        categoriesAdapter.set(categories)


    }

}