package com.example.nickelffoxassignments_sheenu.stopwatch.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.databinding.FragmentStopWatchBinding
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchRepository
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.adapter.StopWatchAdapter
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchWorker
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.viewmodel.StopWatchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class StopWatchFragment :Fragment() {

    private  var _binding: FragmentStopWatchBinding?=null
    private val binding get() = _binding!!
    private lateinit var stopWatchAdapter: StopWatchAdapter
    private lateinit var lapTimer:String
    private lateinit var stopWatchViewModel:StopWatchViewModel
    private var isReset:Boolean=false


    companion object{
         var isCancelled=true
         var isPlayButton:Boolean=true

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding= FragmentStopWatchBinding.inflate(inflater,container,false)
        val view=binding.root

        stopWatchViewModel= ViewModelProvider(this@StopWatchFragment)[StopWatchViewModel::class.java]
        stopWatchAdapter= StopWatchAdapter()
        stopWatchViewModel.repository.newsDatabase.getLapItemsDao().getLapItems().observe(viewLifecycleOwner
        ) {
            stopWatchAdapter.submitList(it)
        }

        binding.btnReset.setOnClickListener {
            if(!isCancelled){
                StopWatchRepository.workerLiveData.postValue(0)
                StopWatchRepository.secondsValue=0
                stopWatchViewModel.repository.stopStopWatchService()
                isCancelled=true
            }else{

                stopWatchViewModel.stopStopWatchRequest()
                isCancelled=true
            }
            CoroutineScope(Dispatchers.IO).launch{
                stopWatchViewModel.deleteLapItems()
            }

        }


        binding.ivBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLap.setOnClickListener {

            lapTimer=binding.tvTimer.text.toString()
            isReset= binding.rvLapItem.isEmpty()
            if(!isPlayButton){
                CoroutineScope(Dispatchers.IO).launch {
                    stopWatchViewModel.insertLapItems(lapTimer,isReset)
                }
            }



//            lapTimeItems.add(StopWatchLapItems(idNo,lapTimer))
//            stopWatchAdapter.submitList(lapTimeItems)
//            binding.rvLapItem.adapter=stopWatchAdapter

        }
        binding.rvLapItem.adapter=stopWatchAdapter

        binding.rvLapItem.layoutManager=LinearLayoutManager(this.activity)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StopWatchWorker.playButtonLiveData.observe(viewLifecycleOwner) {
            isPlayButton = if (it == true) {
                binding.ibPlayButton.setImageResource(R.drawable.pause)
                false

            } else {
                binding.ibPlayButton.setImageResource(R.drawable.play_button)
                true

            }
        }

        StopWatchRepository.workerLiveData.observe(viewLifecycleOwner) { sec ->
            val hours = sec / 3600
            val min = (sec % 3600) / 60
            val second = sec % 60
            val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, min, second)
            binding.tvTimer.text = time
        }

        binding.ibPlayButton.setOnClickListener {
            if(isPlayButton){
                isCancelled=true
                isPlayButton=false
                stopWatchViewModel.createStopWatchRequest()
            }else{
                isCancelled=false
                isPlayButton=true
                stopWatchViewModel.pauseStopWatchRequest()
            }
        }




    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null


    }


}