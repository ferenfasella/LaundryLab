package com.yln.laundrylab.splashFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yln.laundrylab.databinding.FragmentViewPagerBinding
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {
    lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            com.yln.laundrylab.R.layout.fragment_view_pager,
            container, false)
        binding = FragmentViewPagerBinding.bind(view)
        val fragmentList = arrayListOf(
            Onboarding1Fragment(),
            Onboarding2Fragment(),
            Onboarding3Fragment()
        )

        val adapter = ViewPagerAdapter(fragmentList,
            requireActivity().supportFragmentManager, lifecycle)

        view.viewPager.adapter = adapter
//        binding.viewPager.adapter = adapter
        return view
    }
}