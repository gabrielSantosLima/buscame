package com.app.buscame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.dialogs.ClearHistoricDialogFragment
import com.app.buscame.adapters.HistoricAdapter
import com.app.buscame.R
import com.app.buscame.features.historic.HistoricManagerJson
import kotlinx.android.synthetic.main.fragment_historic.*

class HistoricFragment : Fragment(),View.OnClickListener {

    private lateinit var historicManagerJson: HistoricManagerJson

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        bt_clear_historic.setOnClickListener(this)
        historicManagerJson = HistoricManagerJson(context?.applicationContext?.filesDir?.path!!)
        initRecycleView()
    }

    private fun initRecycleView() {
        val historic = historicManagerJson.groupByDate()
        setAdapterOnRecycleView(HistoricAdapter(historic,context?.applicationContext!!))
    }

    private fun setAdapterOnRecycleView(historicAdapter: HistoricAdapter) {
        list_historic.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context?.applicationContext!!)
            adapter = historicAdapter
        }

    }

    override fun onClick(v: View?) {
        ClearHistoricDialogFragment(context?.applicationContext!!).show(childFragmentManager,"ClearHistoricDialogFragment")
    }
}