package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(Runnable { showMainActivity() }, 1500)
    }

    private fun showMainActivity() {
        val intent = Intent(
            this@SplashActivity, TutorialOne::class.java
        )
        startActivity(intent)
        finish()
    }
}
