package com.example.nickelffoxassignments_sheenu.stopwatch.ui.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.databinding.FragmentStopWatchBinding
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.adapter.StopWatchAdapter
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchLapItems
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchWorker
import java.util.*
import kotlin.collections.ArrayList


class StopWatchFragment :Fragment() {

    private  var _binding: FragmentStopWatchBinding? =null
    private val binding get() = _binding!!
    private lateinit var stopWatchAdapter: StopWatchAdapter
    private lateinit var lapTimeItems:ArrayList<StopWatchLapItems>
    private var idNo=0
    private lateinit var lapTimer:String
    var isPlayButton:Boolean=true
     var updatedTime:Int=0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding= FragmentStopWatchBinding.inflate(inflater,container,false)
        val view=binding.root

        lapTimeItems=ArrayList()




       val stopWatchWorkRequest= OneTimeWorkRequestBuilder<StopWatchWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()


//        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(stopWatchWorkRequest.id).observe(viewLifecycleOwner,
//            androidx.lifecycle.Observer {
//                if(it !=null){
//                    updatedTime=it.outputData.getInt("SECONDS",10)
//                }
//            })
        WorkManager.getInstance(requireContext())
        binding.ibPlayButton.setOnClickListener {
            if(isPlayButton==true){
                binding.ibPlayButton.setImageResource(R.drawable.pause)
                WorkManager.getInstance(requireContext())
                    .enqueueUniqueWork("FirstWork", ExistingWorkPolicy.KEEP, stopWatchWorkRequest)
                isPlayButton=false
            }else{
                binding.ibPlayButton.setImageResource(R.drawable.play_button)
                 WorkManager.getInstance(requireContext()).cancelUniqueWork("FirstWork")
                StopWatchWorker.workerLiveData.postValue(updatedTime)


                isPlayButton=true
            }
        }

        binding.btnReset.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelUniqueWork("FirstWork")
           isPlayButton=true
            binding.ibPlayButton.setImageResource(R.drawable.play_button)

        }
        stopWatchAdapter= StopWatchAdapter()

        binding.btnLap.setOnClickListener {

            idNo++
            lapTimer=binding.tvTimer.text.toString()
            lapTimeItems.add(StopWatchLapItems(idNo,lapTimer))
            stopWatchAdapter.submitList(lapTimeItems)
            binding.rvLapItem.adapter=stopWatchAdapter

        }




        binding.rvLapItem.layoutManager=LinearLayoutManager(this.activity)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StopWatchWorker.workerLiveData.observe(viewLifecycleOwner) { sec ->
            val hours = sec / 3600
            val min = (sec % 3600) / 60
            val second = sec % 60
            val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, min, second)
            binding.tvTimer.text = time
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}