package com.example.nickelffoxassignments_sheenu.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R


class HistoryFragment : Fragment() {

    private lateinit var tvHistory: RecyclerView
    private lateinit var calculatorViewModel:CalculatorViewModel
    private lateinit var adapterV:CalculatorAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_history, container, false)
        calculatorViewModel= ViewModelProvider(this@HistoryFragment,CalculatorViewModelFactory(requireActivity().application))[CalculatorViewModel::class.java]
        tvHistory=view.findViewById(R.id.tvHistoryContainer)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculatorViewModel.calculatorRepository.cDatabase.getCalculatorDAO().getCalculation().observe(viewLifecycleOwner
        ) {
            adapterV = CalculatorAdapter(requireContext(), it)
            val layoutManager = LinearLayoutManager(requireContext())
            tvHistory.adapter = adapterV
            tvHistory.layoutManager = layoutManager
        }
    }


}