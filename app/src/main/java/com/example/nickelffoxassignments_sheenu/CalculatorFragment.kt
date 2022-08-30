package com.example.nickelffoxassignments_sheenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat

class CalculatorFragment : MainBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        requireActivity().window.statusBarColor=ContextCompat.getColor(requireContext(),R.color.calculator_color)
//        baseActivity?.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#D79A9A")))
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onStart() {
        super.onStart()

    }

//    override fun hidetoolbar(): Boolean
//        return false
//    }


}