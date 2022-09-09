package com.example.nickelffoxassignments_sheenu.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R


class HistoryFragment : Fragment() {

    lateinit var tvHistorty: RecyclerView
    lateinit var calculatorViewModel:CalculatorViewModel
    lateinit var adapterV:CalculatorAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_history, container, false)
        calculatorViewModel= ViewModelProvider(this@HistoryFragment,CalculatorViewModelFactory(requireActivity().application)).get(CalculatorViewModel::class.java)
        tvHistorty=view.findViewById(R.id.tvHistoryContainer)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculatorViewModel.calculatorRepository.cDatabase.getCalulatorDAO().getCalculation().observe(viewLifecycleOwner,
            Observer {
                adapterV= CalculatorAdapter(requireContext(),it)
                var layoutManager= LinearLayoutManager(requireContext())
                tvHistorty.adapter=adapterV
                tvHistorty.layoutManager=layoutManager
            })
    }


}