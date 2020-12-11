package com.app.buscame.adapters

import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.R
import com.app.buscame.dto.HistoryDto
import com.app.buscame.features.historic.HistoricManagerJson
import com.app.buscame.features.redirectToPage.RedirectToPage
import com.app.buscame.utils.formatTimeByDefaultPattern
import kotlinx.android.synthetic.main.list_view_history.view.*

class HistoryAdapter(private val history : List<HistoryDto>, val fragment: Fragment) :  RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(itemView: View, val fragment: Fragment) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        private val historicManagerJson = HistoricManagerJson(fragment.requireContext().filesDir.path)

        init {
            itemView.card.setOnClickListener { openUrl(it) }
        }

        fun bindHistory(history: HistoryDto){
            itemView.txt_time.text = formatTimeByDefaultPattern(history.date)
            itemView.txt_site.text = history.url
            itemView.txt_term.text = history.term
            itemView.card.tag = history
            itemView.bt_delete.tag = history
            itemView.bt_delete.setOnClickListener(this)
        }

        private fun openUrl(v: View){
            val history = v.tag as HistoryDto
            val redirectToPage = RedirectToPage(fragment)
            redirectToPage.redirect(history.url)
        }

        override fun onClick(v: View?) {
            val history = v?.tag as HistoryDto
            historicManagerJson.remove(history.id!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_history, parent, false)
        return HistoryViewHolder(view, fragment)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindHistory(history[position])
    }
}