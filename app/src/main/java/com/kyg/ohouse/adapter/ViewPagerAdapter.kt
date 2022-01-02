package com.kyg.ohouse.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kyg.ohouse.view.HomeFragment
import com.kyg.ohouse.view.PhotoFeedFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentArrayList: ArrayList<Fragment> =
        arrayListOf(HomeFragment(), PhotoFeedFragment())

    override fun getItemCount(): Int = fragmentArrayList.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragmentArrayList[0]
            1 -> fragmentArrayList[1]
            else -> fragmentArrayList[0]
        }
    }
}