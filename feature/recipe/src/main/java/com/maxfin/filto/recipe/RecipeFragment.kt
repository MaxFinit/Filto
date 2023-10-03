package com.maxfin.filto.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxfin.filto.recipe.databinding.FragmentRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    private val args : RecipeFragmentArgs by navArgs()

    private val binding : FragmentRecipeBinding by viewBinding()

    private val viewModel : RecipeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        viewModel.detailsMeal(args.idMeal)
        observeDetailsMeal()
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeDetailsMeal() {
      
    }



}