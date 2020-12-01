package com.app.buscame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.buscame.R
import kotlinx.android.synthetic.main.activity_main_opened.*

class MainOpenedActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_opened)
        setupBottomNavigation(nav_host.findNavController())
    }

    private fun setupBottomNavigation(navController: NavController) {
        bottom_nav.setupWithNavController(navController)
        bottom_nav.setOnNavigationItemSelectedListener{
            NavigationUI.onNavDestinationSelected(it, nav_host.findNavController())
        }
    }
}