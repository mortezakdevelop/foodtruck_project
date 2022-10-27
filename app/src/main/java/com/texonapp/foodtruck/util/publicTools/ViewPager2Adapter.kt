package com.texonapp.foodtruck.util.publicTools

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter (fragmentManager: FragmentManager, b: Lifecycle) :
    FragmentStateAdapter(fragmentManager, b) {

    private val fragmentList: MutableList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    val fragments: List<Fragment>
        get() = fragmentList

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

}