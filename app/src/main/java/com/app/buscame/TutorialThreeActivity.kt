package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.tutorial_three.*

class TutorialThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_three)

        bt_skip.setOnClickListener {
            openNextActivity()
        }
    }

    private fun openNextActivity() {
        val intent = Intent(this, SearchByTextActivity::class.java)
        val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(applicationContext, R.anim.move_left, R.anim.move_left)
        ActivityCompat.startActivities(this, arrayOf(intent), activityOptionsCompat.toBundle())
    }

}