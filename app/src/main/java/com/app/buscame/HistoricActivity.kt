package com.app.buscame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.features.historic.HistoricManagerJson
import kotlinx.android.synthetic.main.activity_historic.*

class HistoricActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var historicManagerJson: HistoricManagerJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)

        bt_clear_historic.setOnClickListener(this)

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

    override fun onClick(v: View?) {
        ClearHistoricDialogFragment(this).show(supportFragmentManager,"ClearHistoricDialogFragment")
    }
}