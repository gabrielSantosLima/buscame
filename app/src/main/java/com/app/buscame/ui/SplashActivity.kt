package com.app.buscame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.buscame.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val DURATION_SPLASH: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GlobalScope.launch {
            delay(DURATION_SPLASH)
            showMainActivity()
        }
    }

    private fun showMainActivity() {
        val intent = Intent(
            this@SplashActivity, MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
