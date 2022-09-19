package com.example.nickelffoxassignments_sheenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class StopWatchFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_stop_watch, container, false)
//        requireActivity().window.statusBarColor= ContextCompat.getColor(requireContext(),R.color.stopWatch_color)
//        baseActivity?.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#8B864E")))
        return view
    }

//    override fun hidetoolbar(): Boolean {
//        return false
//    }


}