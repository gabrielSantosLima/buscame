package com.app.buscame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class TutorialOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_one)

        setupActionBarWithNavController(findNavController(R.id.fragmentTutorialTOne))
}

    override fun onSupportNavigateUp(): Boolean {
        val NavController: NavController = findNavController(R.id.fragmentTutorialTOne)
        return NavController.navigateUp() || super.onSupportNavigateUp()
    }
}