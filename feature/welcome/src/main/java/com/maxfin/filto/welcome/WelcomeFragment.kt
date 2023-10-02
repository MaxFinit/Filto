package com.maxfin.filto.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxfin.filto.welcome.databinding.FragmentWelcomeBinding
import com.maxfin.filto.welcome.databinding.FragmentWelcomeItemBinding


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val binding: FragmentWelcomeBinding by viewBinding()

    private val dataFragments = mutableListOf(
        WelcomeFragmentItem(
            R.drawable.ic_welcome_bbq, com.maxfin.filto.ui.R.string.welcome_1_title,
            com.maxfin.filto.ui.R.string.welcome_1_text
        ),
        WelcomeFragmentItem(
            R.drawable.ic_welcome_breakfast, com.maxfin.filto.ui.R.string.welcome_2_title,
            com.maxfin.filto.ui.R.string.welcome_2_text
        ),
        WelcomeFragmentItem(
            R.drawable.ic_welcome_smartphone, com.maxfin.filto.ui.R.string.welcome_3_title,
            com.maxfin.filto.ui.R.string.welcome_3_text
        ),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val adapter = ViewPageAdapter(requireActivity(), dataFragments)

        binding.welcomePager.adapter = adapter
        binding.welcomeDots.attachTo(binding.welcomePager)

        binding.welcomePager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == dataFragments.lastIndex) {
                    binding.next.text = getString(com.maxfin.filto.ui.R.string.button_go)
                } else {
                    binding.next.text = getString(com.maxfin.filto.ui.R.string.button_next)
                }
            }
        })

        binding.next.setOnClickListener {
            val position = binding.welcomePager.currentItem
            if (position == dataFragments.lastIndex) {
                findNavController().navigate(com.maxfin.filto.navigation.R.id.action_welcomeFragment_to_feedFragment)
//                startNewNavigation(R.id.homeFragment)
//                if (getMainActivity().mainViewModel.isSubOnboarding){
//                    saveNavigate(R.id.action_homeFragment_to_paywallFragment)
//                }
            } else {
                binding.welcomePager.setCurrentItem(position.plus(1), true)
            }
        }
    }

    class ViewPageAdapter(
        activity: FragmentActivity,
        private val dataFragments: MutableList<WelcomeFragmentItem>
    ) : FragmentStateAdapter(activity) {


        override fun getItemCount(): Int = dataFragments.size

        override fun createFragment(position: Int): Fragment = dataFragments[position]

    }


    class WelcomeFragmentItem(
        private val image: Int,
        private val textResource: Int,
        private val titleResource: Int
    ) : Fragment(R.layout.fragment_welcome_item) {

        private val binding: FragmentWelcomeItemBinding by viewBinding()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.image.setImageResource(image)
            binding.title.text = getString(textResource)
            binding.text.text = getString(titleResource)
        }


    }


}