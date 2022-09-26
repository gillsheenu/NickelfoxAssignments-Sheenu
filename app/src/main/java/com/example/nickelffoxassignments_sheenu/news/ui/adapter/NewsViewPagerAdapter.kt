package com.example.nickelffoxassignments_sheenu.news.ui.adapter

import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nickelffoxassignments_sheenu.news.ui.view.NewsBookmarkFragment
import com.example.nickelffoxassignments_sheenu.news.ui.view.NewsSearchFragment
import com.example.nickelffoxassignments_sheenu.news.ui.view.NewsHomeFragment

@OptIn(ExperimentalPagingApi::class)
class NewsViewPagerAdapter(var fragment:Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> NewsSearchFragment()
            1-> NewsHomeFragment()
            else-> NewsBookmarkFragment()
        }
    }
}