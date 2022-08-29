package com.example.nickelffoxassignments_sheenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(var imagesList:ArrayList<Int>):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){



    class ViewPagerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
         var mImage=itemView.findViewById<ImageView>(R.id.IVViewpagerImages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.viewpager_items,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.mImage.setImageResource(imagesList[position])
        holder.mImage.setOnClickListener {
            when(holder.adapterPosition){
                0->{
                   it.findNavController().navigate(R.id.action_mainFragment_to_calculatorFragment)
                }
                1->{
                    it.findNavController().navigate(R.id.action_mainFragment_to_stopWatchFragment)
                }
                2->{
                    it.findNavController().navigate(R.id.action_mainFragment_to_newsFragment)
                }
                else->{
                    it.findNavController().navigate(R.id.action_mainFragment_to_musicPlayerFragment)

                }
            }
        }

    }

    override fun getItemCount(): Int {
        return imagesList.size
    }


}