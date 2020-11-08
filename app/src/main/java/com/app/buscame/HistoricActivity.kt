package com.app.buscame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.features.historic.HistoricManagerJson
import kotlinx.android.synthetic.main.activity_historic.*

class HistoricActivity : AppCompatActivity() {

    private lateinit var historicManagerJson: HistoricManagerJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)

        historicManagerJson = HistoricManagerJson(applicationContext.filesDir.path)
        initRecycleView()
    }

    private fun initRecycleView() {
        val historic = historicManagerJson.groupByDate()

        setAdapterOnRecycleView(HistoricAdapter(historic,applicationContext))
    }

    private fun setAdapterOnRecycleView(historicAdapter: HistoricAdapter) {
        list_historic.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = historicAdapter
        }

    }
}