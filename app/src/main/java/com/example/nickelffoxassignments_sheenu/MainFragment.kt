package com.example.nickelffoxassignments_sheenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth


class MainFragment : MainBaseFragment() {

    lateinit var viewPager: ViewPager2
    lateinit var imagess:ArrayList<Int>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        imagess=ArrayList<Int>()
        imagess.add(R.drawable.calculator)
        imagess.add(R.drawable.stopwatch)
        imagess.add(R.drawable.mnews)
        imagess.add(R.drawable.music)

//        var btnLogout = view.findViewById<Button>(R.id.btnLogout)
//        firebaseAuth = FirebaseAuth.getInstance()
//        btnLogout.setOnClickListener {
//            firebaseAuth.signOut()
//            findNavController().navigate(R.id.action_mainFragment_to_loginFragment2)
//        }


        var adapter = ViewPagerAdapter(imagess)

        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                updateBgColor()

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }

            override fun onPageScrollStateChanged(state: Int) {
                updateBgColor()
                super.onPageScrollStateChanged(state)
            }
        })



        return view
    }



    private fun updateBgColor() {
        when(viewPager.currentItem){
            0-> viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.calculator_color))
            1->viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.stopWatch_color))
            2->viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.news_color))
            3->viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.musicPlayer_color))

        }
    }

//    override fun hidetoolbar(): Boolean {
//        return true
//    }
}




