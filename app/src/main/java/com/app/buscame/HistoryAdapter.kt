package com.app.buscame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.dto.HistoryDto
import com.app.buscame.utils.formatDateByPattern
import kotlinx.android.synthetic.main.list_view_history.view.*

class HistoryAdapter(private val history : List<HistoryDto>) :  RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindHistory(history: HistoryDto){
            itemView.txt_time.text = formatDateByPattern("hh:mm", history.date)
            itemView.txt_site.text = history.url
            itemView.txt_term.text = history.term
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_history, parent, false)

        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindHistory(history[position])
    }

}