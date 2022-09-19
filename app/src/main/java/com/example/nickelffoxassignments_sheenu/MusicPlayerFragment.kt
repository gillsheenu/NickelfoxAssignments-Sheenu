package com.example.nickelffoxassignments_sheenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class MusicPlayerFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_music_player, container, false)

//        requireActivity().window.statusBarColor= ContextCompat.getColor(requireContext(),R.color.musicPlayer_color)
//        baseActivity?.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#DBDB70")))
        return view
    }

//    override fun hideToolbar(): Boolean {
//        return false
//    }
}
