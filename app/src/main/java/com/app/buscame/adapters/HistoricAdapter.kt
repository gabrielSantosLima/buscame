package com.app.buscame.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.R
import com.app.buscame.dto.HistoryDto
import com.app.buscame.features.historic.HistoricManagerJson
import kotlinx.android.synthetic.main.list_view_historic.view.*

class HistoricAdapter(val fragment: Fragment) :  RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>(){

    val historicManagerJson = HistoricManagerJson(fragment.requireContext().filesDir.path)
    val historic = historicManagerJson.groupByDate()

    class HistoricViewHolder(itemView: View, val fragment : Fragment) : RecyclerView.ViewHolder(itemView){
        fun bindHistoric(date:String, history: List<HistoryDto>){
            val historicAdapter = HistoryAdapter(history, fragment)

            itemView.txt_date.text = date
            itemView.list_history.apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(fragment.requireContext())
                adapter = historicAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_historic, parent, false)

        return HistoricViewHolder(view, fragment)
    }

    override fun getItemCount(): Int = historic.size

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        var count = 0

        for((key,value) in historic){
            if(count == position){
                holder.bindHistoric(key, value)
                break
            }
            count++
            continue
        }
    }

}