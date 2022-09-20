package com.example.nickelffoxassignments_sheenu.news.models

import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nickelffoxassignments_sheenu.news.fragment.NewsBookmarkFragment
import com.example.nickelffoxassignments_sheenu.news.fragment.NewsHomeFragment
import com.example.nickelffoxassignments_sheenu.news.fragment.NewsSearchFragment

@OptIn(ExperimentalPagingApi::class)
class NewsViewPagerAdapter(var fragment:Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> NewsHomeFragment()
            1-> NewsSearchFragment()
            else-> NewsBookmarkFragment()
        }
    }
}