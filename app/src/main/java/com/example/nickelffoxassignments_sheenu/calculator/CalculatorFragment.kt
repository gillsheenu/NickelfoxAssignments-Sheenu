package com.example.nickelffoxassignments_sheenu.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.nickelffoxassignments_sheenu.R

class CalculatorFragment : Fragment() {

    lateinit var tvOne: TextView
    lateinit var tvTwo: TextView
    lateinit var tvThree: TextView
    lateinit var tvFour: TextView
    lateinit var tvFive: TextView
    lateinit var tvSix: TextView
    lateinit var tvSeven: TextView
    lateinit var tvEight: TextView
    lateinit var tvNine: TextView
    lateinit var tvZero: TextView
    lateinit var tvDoubleZero: TextView
    lateinit var tvDecimal: TextView
    lateinit var tvAddition: TextView
    lateinit var tvMultiplication: TextView
    lateinit var tvDivision: TextView
    lateinit var tvSubtraction: TextView
    lateinit var tvPercentage: TextView
    lateinit var tvAllClear: TextView
    lateinit var tvClear: TextView
    lateinit var tvInput: TextView
    lateinit var tvOutput: TextView
    lateinit var tvEquals: TextView
    lateinit var expression :String
    var isPressed=false
    var isNumber=false
    var inputValue1=""
    var input=0.0
    var result=0.0
    var operands=ArrayList<Double>()
    var operators=ArrayList<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        requireActivity().window.statusBarColor=ContextCompat.getColor(requireContext(),R.color.calculator_color)
//        baseActivity?.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#D79A9A")))

        var mview=inflater.inflate(R.layout.fragment_calculator, container, false)

        initialization(mview)
        setListeners()
        return mview
    }

    private fun setListeners() {
        tvOne.setOnClickListener {
            isNumber=true
            setInput("1")
        }
        tvTwo.setOnClickListener {
            isNumber=true
            setInput("2")
        }
        tvThree.setOnClickListener {
            isNumber=true
            setInput("3")
        }
        tvFour.setOnClickListener {
            isNumber=true
            setInput("4")
        }
        tvFive.setOnClickListener {
            isNumber=true
            setInput("5")
        }
        tvSix.setOnClickListener {
            isNumber=true
            setInput("6")
        }
        tvSeven.setOnClickListener {
            isNumber=true
            setInput("7")
        }
        tvEight.setOnClickListener {
            isNumber=true
            setInput("8")
        }
        tvNine.setOnClickListener {
            isNumber=true
            setInput("9")
        }
        tvZero.setOnClickListener {
            isNumber=true
            setInput("0")
        }
        tvDoubleZero.setOnClickListener {
            isNumber=true
            setInput("00")
        }
        tvClear.setOnClickListener {

//            if(isNumber==true){
//                inputValue1.subSequence(0,inputValue1.length-1)
//            }else{
//                operators.removeAt(tvInput.length()-1)
//            }
            if(tvInput.length()>0){
                tvInput.text=tvInput.text.subSequence(0,tvInput.length()-1)
            }
        }
        tvDecimal.setOnClickListener {
            isNumber=true
            setInput(".")
        }
        tvAllClear.setOnClickListener {
            isPressed=false
            tvInput.text=""
            tvOutput.text=""
            inputValue1=""
            input=0.0
            operands.clear()
            operators.clear()
        }
        tvAddition.setOnClickListener {
            isNumber=false
            setInput("+")
        }
        tvSubtraction.setOnClickListener {
            isNumber=false
            setInput("-")
        }
        tvMultiplication.setOnClickListener {
            isNumber=false
            setInput("*")
        }
        tvDivision.setOnClickListener {
            isNumber=false
            setInput("/")
        }
        tvEquals.setOnClickListener {
            if(isPressed==true){
                isNumber = false
                tvInput.text=""
                tvOutput.text=""
                tvInput.text=input.toString()
                inputValue1=input.toString()
                input=0.0
                isPressed=false
            }else{
                if(inputValue1!=""){
                    operands.add(inputValue1.toDouble())
                    inputValue1 = ""
                    input= performCalculation(expression)
                    tvOutput.text=input.toString()
                    operands.clear()
                    operators.clear()
                    isPressed=true
                }else{
                    Toast.makeText(context,"Add value",Toast.LENGTH_SHORT).show()
                }

            }

        }
        tvPercentage.setOnClickListener {
            inputValue1=inputValue1.toDouble().div(100).toString()
            tvInput.text=tvInput.text.toString()+"%"

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
                    var result = operands[i + 1].times(operands[i])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else if (operands.last() == operands[i]) {
                    var result = operands[i].times(i - 1)
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)
                } else if (operands.first() == operands[i]) {
                    var result = operands[i + 1].times(operands[i])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else {
                    var result = operands[i + 1].times(operands[i])
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
                    var result = operands[i].plus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else if (operands.last() == operands[i]) {
                    var result = operands[i-1].plus(operands[i])
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)
                } else if (operands.first() == operands[i]) {
                    var result = operands[i].plus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else {
                    var result = operands[i].plus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                }
            }
            "-"->{
                if (operands.last() == operands[i + 1]) {
                    var result = operands[i].minus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else if (operands.last() == operands[i]) {
                    var result = operands[i-1].minus(operands[i])
                    operands[i - 1] = result
                    operands.removeAt(i)
                    operators.removeAt(i)
                } else if (operands.first() == operands[i]) {
                    var result = operands[i].minus(operands[i+1])

                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                } else {
                    var result = operands[i].minus(operands[i+1])
                    operands[i] = result
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                }
            }
        }

    }




    private fun setInput(pos: String) {

        if(isPressed==true){
            if(isNumber==false){
                expression = tvInput.text.toString()
                expression = expression + pos
                tvInput.text = expression
                operands.add(input)
                if (pos == "+" || pos == "*" || pos == "-" || pos == "/") {
                    operators.add(pos)
                    isPressed=false

                }

            }else{
                Toast.makeText(context,"Add operator",Toast.LENGTH_SHORT).show()
            }

        }else{

            if (isNumber==true) {
                expression = tvInput.text.toString()
                expression = expression + pos
                tvInput.text = expression
                inputValue1 = inputValue1 + pos
            } else {
                if(inputValue1!=""){
                    expression = tvInput.text.toString()
                    expression = expression + pos
                    tvInput.text = expression
                    operands.add(inputValue1.toDouble())
                    inputValue1 = ""
                    if (pos == "+" || pos == "*" || pos == "-" || pos == "/") {
                        operators.add(pos)

                    }
                }else{
                    Toast.makeText(context,"Add values",Toast.LENGTH_SHORT).show()
                }

            }
        }

    }




    private fun initialization(view: View) {
        tvOne = view.findViewById(R.id.tvOne)
        tvTwo = view.findViewById(R.id.tvTwo)
        tvThree = view.findViewById(R.id.tvThree)
        tvFour = view.findViewById(R.id.tvFour)
        tvFive = view.findViewById(R.id.tvFive)
        tvSix = view.findViewById(R.id.tvSix)
        tvSeven = view.findViewById(R.id.tvSeven)
        tvEight = view.findViewById(R.id.tvEight)
        tvNine = view.findViewById(R.id.tvNine)
        tvZero = view.findViewById(R.id.tvZero)
        tvDoubleZero = view.findViewById(R.id.tvDoubleZero)
        tvDecimal = view.findViewById(R.id.tvDecimal)
        tvPercentage = view.findViewById(R.id.tvPercentage)
        tvMultiplication = view.findViewById(R.id.tvMultiplication)
        tvDivision = view.findViewById(R.id.tvDivision)
        tvAddition = view.findViewById(R.id.tvAddition)
        tvSubtraction = view.findViewById(R.id.tvSubtraction)
        tvEquals = view.findViewById(R.id.tvEquals)
        tvAllClear = view.findViewById(R.id.tvAllclear)
        tvInput = view.findViewById(R.id.tvScreenCalculation)
        tvOutput = view.findViewById(R.id.tvScreenResult)
        tvClear = view.findViewById(R.id.tvClear)

    }



//    override fun hidetoolbar(): Boolean
//        return false
//    }


}