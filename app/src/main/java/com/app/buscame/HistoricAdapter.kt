package com.app.buscame

import android.content.Context
import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.dto.HistoryDto
import kotlinx.android.synthetic.main.list_view_historic.view.*

class HistoricAdapter(private val historic : Map<String, List<HistoryDto>>, private val applicationContext: Context) :  RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>(){

    class HistoricViewHolder(itemView: View, private val applicationContext: Context) : RecyclerView.ViewHolder(itemView){
        fun bindHistoric(date:String, history: List<HistoryDto>){
            val historicAdapter = HistoryAdapter(history, applicationContext)

            itemView.txt_date.text = date
            itemView.list_history.apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = historicAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_historic, parent, false)

        return HistoricViewHolder(view, applicationContext)
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