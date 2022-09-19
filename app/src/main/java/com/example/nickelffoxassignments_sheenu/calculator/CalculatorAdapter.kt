package com.example.nickelffoxassignments_sheenu.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R

class CalculatorAdapter(val context: Context, val list:List<Calculations>): RecyclerView.Adapter<CalculatorAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        var id= itemView.findViewById<TextView>(R.id.tvId)!!
        var expr= itemView.findViewById<TextView>(R.id.tvExpression)!!
        var result= itemView.findViewById<TextView>(R.id.tvResult)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView= LayoutInflater.from(context).inflate(R.layout.calculator_adapter,null)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text=list[position].id.toString()
        holder.expr.text=list[position].expression
        holder.result.text=list[position].result
    }

    override fun getItemCount(): Int {
        return list.size
    }
}