package com.app.buscame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_tutorial_two.*


class TutorialTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnAdvance.setOnClickListener {
            findNavController().navigate(R.id.action_tutorialTwoFragment_to_tutorialThreeFragment)
        }
    }
}