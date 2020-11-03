package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    private val DURATION_SPLASH: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed(Runnable { showMainActivity() }, DURATION_SPLASH)
    }

    private fun showMainActivity() {
        val intent = Intent(
            this@SplashActivity, FavoriteActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
