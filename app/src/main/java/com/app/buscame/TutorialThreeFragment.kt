package com.app.buscame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_tutorial_three.*

class TutorialThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_three, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_skip.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_tutorialThreeFragment_to_mainActivity2)
        }
    }
}