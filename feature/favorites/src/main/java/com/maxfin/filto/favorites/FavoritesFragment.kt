package com.maxfin.filto.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxfin.filto.favorites.databinding.FragmentFavoritesBinding
import com.maxfin.filto.navigation.saveNavigate
import com.maxfin.filto.ui.dp
import com.maxfin.filto.ui.recycler.Spacing
import com.maxfin.filto.ui.recycler.SpacingItemDecoration
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding: FragmentFavoritesBinding by viewBinding()

    private val viewModel: FavoritesViewModel by viewModels()

    private val favoritesMealAdapter = ItemAdapter<FavoritesMealHolder>()
    private val favoritesMealFastAdapter = FastAdapter.with(favoritesMealAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavoritesMealList()
    }

    private fun initFavoritesMealList() {
        binding.favoritesList.adapter = favoritesMealFastAdapter
        binding.favoritesList.addItemDecoration(SpacingItemDecoration(Spacing(vertical = 4.dp)))

        favoritesMealFastAdapter.onClickListener = { view, adapter, item, position ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToRecipeFragment(
                item.tag.toString()
            )
            saveNavigate(action)
            true
        }

        observeFavorites()
    }

    private fun observeFavorites() {
        viewModel.favoritesMeal.observe(viewLifecycleOwner) { meal ->
            val favList = mutableListOf<FavoritesMealHolder>()

            meal.forEach {
                favList.add(FavoritesMealHolder(it))
            }

            favoritesMealAdapter.set(favList)
        }
    }

}