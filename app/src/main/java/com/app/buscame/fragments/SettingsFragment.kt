package com.app.buscame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.app.buscame.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        bt_about.setOnClickListener { toAbout() }
        layoutHeaderButton.setOnClickListener{ toAbout() }
        bt_back.setOnClickListener { back() }

        toggle_dark.setOnCheckedChangeListener {_, isChecked ->
            print(isChecked)
        }
    }

    fun back(){
        NavHostFragment.findNavController(this)
            .popBackStack()
    }

    private fun toAbout(){
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_settingsFragment_to_aboutFragment)
    }
}