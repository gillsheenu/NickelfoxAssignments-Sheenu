package com.example.nickelffoxassignments_sheenu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.widget.ViewPager2

@ExperimentalPagingApi
class MainFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var images:ArrayList<Int>
    private var baseActivity: MainActivity? =null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity=context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        images=ArrayList()
        images.add(R.drawable.calculator)
        images.add(R.drawable.stopwatch)
        images.add(R.drawable.mnews)
        images.add(R.drawable.music)

//        var btnLogout = view.findViewById<Button>(R.id.btnLogout)
//        firebaseAuth = FirebaseAuth.getInstance()
//        btnLogout.setOnClickListener {
//            firebaseAuth.signOut()
//            findNavController().navigate(R.id.action_mainFragment_to_loginFragment2)
//        }


        val adapter = ViewPagerAdapter(images)

        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                updateBgColor()
                statusBarColor()

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//
//            }

            override fun onPageScrollStateChanged(state: Int) {
                updateBgColor()
                statusBarColor()
                super.onPageScrollStateChanged(state)
            }
        })



        return view
    }

    private fun statusBarColor() {
        when(viewPager.currentItem){
            0->setColor(R.color.calculator_color)
            1->setColor(R.color.stopWatch_color)
            2->setColor(R.color.news_color)
            3->setColor(R.color.musicPlayer_color)
            else->setColor(R.color.colorPrimary)
        }
    }

    private fun setColor(mColor:Int) {
        requireActivity().window.statusBarColor=ContextCompat.getColor(requireContext(),mColor)
        baseActivity?.supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),mColor))

    }


    private fun updateBgColor() {
        when(viewPager.currentItem){
            0-> viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.calculator_color))
            1->viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.stopWatch_color))
            2->viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.news_color))
            3->viewPager.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.musicPlayer_color))

        }
    }

//    override fun hideToolbar(): Boolean {
//        return true
//    }
}




