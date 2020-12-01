package com.app.buscame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.app.buscame.R
import kotlinx.android.synthetic.main.settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
    imgRunButtton.setOnClickListener{
        openNextActivity()
    }
    }
    private fun openNextActivity() {
        val intent = Intent(this, AboutActivity::class.java)
        val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(applicationContext,
            R.anim.move_left,
            R.anim.move_left
        )
        ActivityCompat.startActivities(this, arrayOf(intent), activityOptionsCompat.toBundle())
    }

}