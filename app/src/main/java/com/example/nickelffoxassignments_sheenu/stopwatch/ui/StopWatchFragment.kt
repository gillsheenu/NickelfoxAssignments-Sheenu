package com.example.nickelffoxassignments_sheenu.stopwatch.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.databinding.FragmentStopWatchBinding
import com.example.nickelffoxassignments_sheenu.stopwatch.data.StopWatchWorker
import java.util.*


class StopWatchFragment :Fragment() {

    private  var _binding: FragmentStopWatchBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding= FragmentStopWatchBinding.inflate(inflater,container,false)
        var view=binding.root

       var stopWatchWorkRequest= OneTimeWorkRequestBuilder<StopWatchWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()

        binding.ibPlayButton.setOnClickListener {
            WorkManager.getInstance(requireContext())
                .enqueueUniqueWork("FirstWork", ExistingWorkPolicy.REPLACE, stopWatchWorkRequest)
        }
        
        binding.btnReset.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelUniqueWork("FirstWork")

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StopWatchWorker.workerLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { sec->
            var hours=sec/3600
            var min=(sec % 3600) /60
            var secn=sec%60
            var time=String.format(Locale.getDefault(), "%d:%02d:%02d",hours,min,secn)
            binding.tvTimer.text=time
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}