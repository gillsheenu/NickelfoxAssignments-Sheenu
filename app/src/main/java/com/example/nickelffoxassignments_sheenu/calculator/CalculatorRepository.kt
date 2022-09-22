package com.example.nickelffoxassignments_sheenu.calculator

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorRepository(var application: Application) {
    private var exp:String=""
    private var isPressed=false
    private var isNumber=false
    private var inputValue1=""
    private var input=0.0
    private var result=0.0
    private var operands=ArrayList<Double>()
    private var operators=ArrayList<String>()
    var inputLiveData= MutableLiveData<String>()
    var outputLiveData= MutableLiveData<String>()

    var cDatabase:CalculationDatabase= Room.databaseBuilder(application, CalculationDatabase::class.java,"calculatorDB").build()



    fun setInput(pos:String,mNumber:Boolean){
        isNumber=mNumber
        if(!isPressed) {
            if (isNumber) {
                exp += pos
                inputLiveData.postValue(exp)
                inputValue1 += pos
            } else {
                if (inputValue1 != "") {
                    exp += pos
                    inputLiveData.postValue(exp)
                    if (pos == "%") {
                        inputValue1 = inputValue1.toDouble().div(100).toString()

                    } else {
                        operands.add(inputValue1.toDouble())
                        inputValue1 = ""
                        if (pos == "+" || pos == "*" || pos == "-" || pos == "/") {
                            operators.add(pos)

                        }
                    }

                } else {
                    Toast.makeText(application, "Add values", Toast.LENGTH_SHORT).show()
                }
            }

        }else {
            Toast.makeText(application,"Press equals Operator", Toast.LENGTH_SHORT).show()
        }
    }


    private fun performCalculation(expression: String):Double{
        while(operators.contains("*")||operators.contains("/")){
            var j=0
            while(j<operators.size){
                if(operators[j]=="*"||operators[j]=="/"){
                    solve(operators[j],j)
                }
                j++
            }
        }

        while(operators.contains("+")||operators.contains("-")){
            var j=0
            while(j<operators.size){
                if(operators[j]=="+"||operators[j]=="-"){
                    solve(operators[j],j)
                }
                j++
            }
        }
        return operands[0]
    }



    private fun solve(s: String, i: Int) {
        when (s) {
            "*" -> {
                if (operands.last() == operands[i + 1]) {
                    // TODO : check later
                    val result = operands[i + 1].times(operands[i])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else if (operands.last() == operands[i]) {
                    val result = operands[i].times(i - 1)
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)
                } else if (operands.first() == operands[i]) {
                    val result = operands[i + 1].times(operands[i])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else {
                    val result = operands[i + 1].times(operands[i])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                }


            }

            "/" -> {
                if (operands.last() == operands[i + 1]) {
                    result = (operands[i]).div(operands[i + 1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)

                } else if (operands.last() == operands[i]) {

                    result = operands[i - 1].div(operands[i])
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)

                } else if (operands.first() == operands[i]) {

                    result = operands[i].div(operands[i + 1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)

                } else {

                    result = operands[i].div(operands[i + 1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)

                }

            }
            "+"->{
                if (operands.last() == operands[i + 1]) {
                    val result = operands[i].plus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else if (operands.last() == operands[i]) {
                    val result = operands[i-1].plus(operands[i])
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)
                } else if (operands.first() == operands[i]) {
                    val result = operands[i].plus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else {
                    val result = operands[i].plus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                }
            }
            "-"->{
                if (operands.last() == operands[i + 1]) {
                    val result = operands[i].minus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else if (operands.last() == operands[i]) {
                    val result = operands[i-1].minus(operands[i])
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)
                } else if (operands.first() == operands[i]) {
                    val result = operands[i].minus(operands[i+1])

                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else {
                    val result = operands[i].minus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                }
            }
        }

    }

    fun equals(){

        if(isPressed) {
            exp = ""
            inputLiveData.postValue(exp)
            exp=input.toString()
            inputLiveData.postValue(exp)
            inputValue1 = input.toString()
            input = 0.0
            outputLiveData.postValue(input.toString())
            isPressed=false

        }else {

            if (inputValue1 != "") {
                operands.add(inputValue1.toDouble())
                inputValue1 = ""
                input = performCalculation(exp)
                outputLiveData.postValue(input.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    cDatabase.getCalculatorDAO().insertExpression(Calculations(0,exp,input.toString()))
                }
                operands.clear()
                operators.clear()
                isPressed = true
            } else {
                Toast.makeText(application, "Add value", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun allClear(){
        isPressed=false
        exp=""
        inputLiveData.postValue(exp)
        outputLiveData.postValue(exp)
        inputValue1=""
        input=0.0
        operands.clear()
        operators.clear()
    }

    fun clear(Len:Int){
        if(isNumber && inputValue1 !=""){
            try{
                inputValue1= inputValue1.subSequence(0,inputValue1.length-1).toString()

            }catch (e:Exception){
                Log.d("NUM", "Clear: ${e.message}")
            }

        }else{
            try{
                operators.removeLast()
                inputValue1=operands.last().toInt().toString()
                if(operands.isNotEmpty()){
                    operands.removeLast()
                }


            }catch (e:Exception){
                Log.d("NUM", "Clear: ${e.message}")
            }


        }
        if(Len>0){
            exp= exp.subSequence(0,exp.length-1) as String
            inputLiveData.postValue(exp)
        }
    }

}