package com.example.nickelffoxassignments_sheenu.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nickelffoxassignments_sheenu.R

class CalculatorFragment : Fragment() {

    private lateinit var tvOne: TextView
    private lateinit var tvTwo: TextView
    private lateinit var tvThree: TextView
    private lateinit var tvFour: TextView
    private lateinit var tvFive: TextView
    private lateinit var tvSix: TextView
    private lateinit var tvSeven: TextView
    private lateinit var tvEight: TextView
    private lateinit var tvNine: TextView
    private lateinit var tvZero: TextView
    private lateinit var tvDoubleZero: TextView
    private lateinit var tvDecimal: TextView
    private lateinit var tvAddition: TextView
    private lateinit var tvMultiplication: TextView
    private lateinit var tvDivision: TextView
    private lateinit var tvSubtraction: TextView
    private lateinit var tvPercentage: TextView
    private lateinit var tvAllClear: TextView
    private lateinit var tvClear: TextView
    private lateinit var tvInput: TextView
    private lateinit var tvOutput: TextView
    private lateinit var tvEquals: TextView
    private lateinit var history: ImageView


    private lateinit var calculatorViewModel:CalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView=inflater.inflate(R.layout.fragment_calculator, container, false)

        calculatorViewModel= ViewModelProvider(this@CalculatorFragment,CalculatorViewModelFactory(
            requireActivity().application))[CalculatorViewModel::class.java]

//
//        CoroutineScope(Dispatchers.IO).launch {
//            cDatabase.getCalculatorDAO().insertExpression(Calculations(0,"12+3",15.0))
//        }


        initialization(mView)

        setListeners()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculatorViewModel.inputValueLiveData.observe(viewLifecycleOwner) {
            tvInput.text = it
        }
        calculatorViewModel.outputValueLiveData.observe(viewLifecycleOwner) {
            tvOutput.text = it
//            if(it!=""){
//                CoroutineScope(Dispatchers.IO).launch {
//                    cDatabase.getCalculatorDAO().insertExpression(Calculations(0,tvInput.text.toString(),it))
//                }
//            }

        }

    }

    private fun setListeners() {
        history.setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_historyFragment)
        }
        tvOne.setOnClickListener {
            calculatorViewModel.setInputValues("1",true)
        }

        tvTwo.setOnClickListener {
            calculatorViewModel.setInputValues("2",true)
        }

        tvThree.setOnClickListener {
            calculatorViewModel.setInputValues("3",true)
        }

        tvFour.setOnClickListener {
            calculatorViewModel.setInputValues("4",true)
        }

        tvFive.setOnClickListener {
            calculatorViewModel.setInputValues("5",true)
        }

        tvSix.setOnClickListener {
            calculatorViewModel.setInputValues("6",true)
        }

        tvSeven.setOnClickListener {
            calculatorViewModel.setInputValues("7",true)
        }

        tvEight.setOnClickListener {
            calculatorViewModel.setInputValues("8",true)
        }

        tvNine.setOnClickListener {
            calculatorViewModel.setInputValues("9",true)
        }

        tvZero.setOnClickListener {
            calculatorViewModel.setInputValues("0",true)
        }

        tvDoubleZero.setOnClickListener {
            calculatorViewModel.setInputValues("00",true)

        }

        tvClear.setOnClickListener {
            calculatorViewModel.clearValue(tvInput.length())
        }

        tvDecimal.setOnClickListener {
            calculatorViewModel.setInputValues(".",true)
        }

        tvAllClear.setOnClickListener {
            calculatorViewModel.allClearValues()
        }

        tvAddition.setOnClickListener {
            calculatorViewModel.setInputValues("+",false)
        }

        tvSubtraction.setOnClickListener {
            calculatorViewModel.setInputValues("-",false)
        }

        tvMultiplication.setOnClickListener {
            calculatorViewModel.setInputValues("*",false)
        }

        tvDivision.setOnClickListener {
            calculatorViewModel.setInputValues("/",false)
        }

        tvEquals.setOnClickListener {
            calculatorViewModel.equals()
        }

        tvPercentage.setOnClickListener {
            calculatorViewModel.setInputValues("%",false)
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
        tvAllClear = view.findViewById(R.id.tvAllClear)
        tvInput = view.findViewById(R.id.tvScreenCalculation)
        tvOutput = view.findViewById(R.id.tvScreenResult)
        tvClear = view.findViewById(R.id.tvClear)
        history=view.findViewById(R.id.IVHistory)

    }



}


