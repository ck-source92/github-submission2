package com.dwicandra.githubuserapi.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dwicandra.githubuserapi.ui.fragment.FollowersFragment
import com.dwicandra.githubuserapi.ui.fragment.FollowingFragment


class SectionPagerAdapter(private val fm: FragmentManager,lifecycle: Lifecycle ,data: Bundle) :
    FragmentStateAdapter(fm, lifecycle) {
    private val fragmentBundle : Bundle = data
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

}