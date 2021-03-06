package com.app.buscame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.app.buscame.R
import kotlinx.android.synthetic.main.fragment_tutorial_one.*

class TutorialOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)

        btnAdvance.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_tutorialOneFragment_to_tutorialTwoFragment)
        }
        bt_skip.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_tutorialOneFragment_to_mainActivityOpened)
        }
    }
}