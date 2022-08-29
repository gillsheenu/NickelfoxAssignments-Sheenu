package com.example.nickelffoxassignments_sheenu

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat


class NewsFragment : MainBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view=inflater.inflate(R.layout.fragment_news, container, false)
        requireActivity().window.statusBarColor= ContextCompat.getColor(requireContext(),R.color.news_color)
        baseActivity?.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#CAC7")))

        return view
    }

//    override fun hidetoolbar(): Boolean {
//        return false
//    }


}