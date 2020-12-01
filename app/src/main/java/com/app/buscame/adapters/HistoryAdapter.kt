package com.app.buscame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.R
import com.app.buscame.dto.HistoryDto
import com.app.buscame.utils.formatTimeByDefaultPattern
import kotlinx.android.synthetic.main.list_view_history.view.*

class HistoryAdapter(private val history : List<HistoryDto>,val applicationContext: Context) :  RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(itemView: View, val applicationContext: Context) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        fun bindHistory(history: HistoryDto){
            itemView.txt_time.text = formatTimeByDefaultPattern(history.date)
            itemView.txt_site.text = history.url
            itemView.txt_term.text = history.term
            itemView.bt_options.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            showPopUp(v!!)
        }

        fun showPopUp(v: View){
            val popup = PopupMenu(applicationContext, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_historic, popup.menu)
            popup.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_history, parent, false)

        return HistoryViewHolder(view, applicationContext)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindHistory(history[position])
    }

}