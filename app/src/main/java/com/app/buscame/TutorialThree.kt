package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.app.buscame.camera.CameraActivity
import kotlinx.android.synthetic.main.tutorial_three.*


class TutorialThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_three)

        btnRun.setOnClickListener {
            openNextActivity()
        }
    }

    private fun openNextActivity() {
        val intent = Intent(this, CameraActivity::class.java)
        val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(applicationContext, R.anim.move_left, R.anim.move_left)
        ActivityCompat.startActivities(this, arrayOf(intent), activityOptionsCompat.toBundle())
    }

}