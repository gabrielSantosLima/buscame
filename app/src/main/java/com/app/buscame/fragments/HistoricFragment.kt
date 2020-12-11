package com.app.buscame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.R
import com.app.buscame.dialogs.ClearHistoricDialogFragment
import com.app.buscame.adapters.HistoricAdapter
import kotlinx.android.synthetic.main.fragment_historic.*

class HistoricFragment : Fragment(), View.OnClickListener {

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
        initRecycleView()
        bt_clear_historic.setOnClickListener(this)
        bt_config.setOnClickListener { toConfig() }
    }

    private fun toConfig() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_historicFragment_to_settingsFragment)
    }

    private fun initRecycleView() {
        setAdapterOnRecycleView(HistoricAdapter(this))
    }

    private fun setAdapterOnRecycleView(historicAdapter: HistoricAdapter) {
        list_historic.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historicAdapter
        }

    }

    override fun onClick(v: View?) {
        ClearHistoricDialogFragment(requireContext()).show(childFragmentManager,"ClearHistoricDialogFragment")
    }
}