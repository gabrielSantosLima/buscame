package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.tutorial_two.*

class TutorialTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_two)

        setupActionBarWithNavController(findNavController(R.id.fragmentTutorialTwo))

    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController: NavController = findNavController(R.id.fragmentTutorialTwo)
        return NavController.navigateUp() || super.onSupportNavigateUp()
    }

}