package com.example.nickelffoxassignments_sheenu.news


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var bottomNavigationView:BottomNavigationView
    lateinit var searchNavHostFragment:NavHostFragment
    lateinit var mNavController:NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_news, container, false)

        bottomNavigationView=view.findViewById(R.id.bnvNews)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchNavHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        mNavController=searchNavHostFragment.navController

        if(mNavController!=null) {
            Log.d("RESU", "onCreateView: no nav controller")
            bottomNavigationView.setupWithNavController(mNavController)
        }else{
            Log.d("RES", "onCreateView: no nav controller")
        }
    }



}