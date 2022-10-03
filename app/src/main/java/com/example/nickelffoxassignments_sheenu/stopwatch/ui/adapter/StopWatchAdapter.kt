package com.example.nickelffoxassignments_sheenu.stopwatch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.databinding.StopwatchAdapterBinding
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchLapItems

class StopWatchAdapter:ListAdapter<StopWatchLapItems,StopWatchAdapter.StopwatchViewHolder>(StopwatchDiffUtilCallback()) {

    class StopwatchViewHolder(private val binding:StopwatchAdapterBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(item:StopWatchLapItems){

           binding.tvIdLap.text=item.id.toString()
            binding.tvTimeLap.text=item.time
        }
    }

    class StopwatchDiffUtilCallback:DiffUtil.ItemCallback<StopWatchLapItems>() {

        override fun areItemsTheSame(oldItem: StopWatchLapItems, newItem: StopWatchLapItems): Boolean {
           return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: StopWatchLapItems, newItem: StopWatchLapItems): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopwatchViewHolder {

        val binding= StopwatchAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StopwatchViewHolder(binding)

    }

    override fun onBindViewHolder(holder: StopwatchViewHolder, position: Int) {

        val item =getItem(position)
        holder.bind(item)

    }


}