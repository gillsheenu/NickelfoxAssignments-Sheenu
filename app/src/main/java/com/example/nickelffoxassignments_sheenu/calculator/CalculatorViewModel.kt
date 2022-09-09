package com.example.nickelffoxassignments_sheenu.calculator

import android.app.Application
import androidx.lifecycle.ViewModel

class CalculatorViewModel(var application: Application): ViewModel() {

    var calculatorRepository=CalculatorRepository(application)

    var inputValueLiveData=calculatorRepository.inputLiveData
    var outputValueLiveData=calculatorRepository.ouputLiveData

    fun setInputValues(pos:String,isNumber:Boolean){
        calculatorRepository.setInput(pos,isNumber)
    }
    fun equals(){
        calculatorRepository.Equal()
    }
    //    fun percentage(){
//        calculatorRepository.percent()
//    }
    fun allClearValues(){
        calculatorRepository.allClear()
    }
    fun clearValue(ln:Int){
        calculatorRepository.Clear(ln)
    }
}