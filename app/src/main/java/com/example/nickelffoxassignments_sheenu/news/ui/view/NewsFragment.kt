package com.example.nickelffoxassignments_sheenu.news.ui.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nickelffoxassignments_sheenu.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var searchNavHostFragment:NavHostFragment
    private lateinit var mNavController:NavController
//    lateinit var viewPager:ViewPager2
//    lateinit var viewPagerAdapter:NewsViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_news, container, false)

        bottomNavigationView=view.findViewById(R.id.bnvNews)

//        viewPager=view.findViewById(R.id.newsViewPager)
//        viewPagerAdapter=NewsViewPagerAdapter(this)
//        viewPager.adapter=viewPagerAdapter



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchNavHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        mNavController=searchNavHostFragment.navController

        bottomNavigationView.setupWithNavController(mNavController)
    }



}