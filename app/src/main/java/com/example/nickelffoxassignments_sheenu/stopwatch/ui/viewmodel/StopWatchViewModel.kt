package com.example.nickelffoxassignments_sheenu.stopwatch.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StopWatchViewModel @Inject constructor(var repository:StopWatchRepository):ViewModel() {

    suspend fun insertLapItems(lapItems:String,isReset:Boolean){
        repository.insertStopWatchLapitems(lapItems,isReset)
    }

    suspend fun deleteLapItems(){
        repository.deleteLapItems()
    }

}