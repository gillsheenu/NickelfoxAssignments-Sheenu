package com.example.nickelffoxassignments_sheenu.calculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
    lateinit var history: ImageView


    lateinit var calculatorViewModel:CalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var mview=inflater.inflate(R.layout.fragment_calculator, container, false)

        calculatorViewModel= ViewModelProvider(this@CalculatorFragment,CalculatorViewModelFactory(
            requireActivity().application)).get(CalculatorViewModel::class.java)

//
//        CoroutineScope(Dispatchers.IO).launch {
//            cDatabase.getCalulatorDAO().insertExpression(Calculations(0,"12+3",15.0))
//        }


        initialization(mview)

        setListeners()
        return mview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculatorViewModel.inputValueLiveData.observe(viewLifecycleOwner, Observer {
            tvInput.text=it
        })
        calculatorViewModel.outputValueLiveData.observe(viewLifecycleOwner, Observer {
            tvOutput.text=it
//            if(it!=""){
//                CoroutineScope(Dispatchers.IO).launch {
//                    cDatabase.getCalulatorDAO().insertExpression(Calculations(0,tvInput.text.toString(),it))
//                }
//            }

        })

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
        tvAllClear = view.findViewById(R.id.tvAllclear)
        tvInput = view.findViewById(R.id.tvScreenCalculation)
        tvOutput = view.findViewById(R.id.tvScreenResult)
        tvClear = view.findViewById(R.id.tvClear)
        history=view.findViewById(R.id.IVHistory)

    }



}


