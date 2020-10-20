package com.app.buscame.features.share

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ShareMessage(private val appCompatActivity: AppCompatActivity, private val title: String) : IShareMessage{

    override fun shareMessage(text: String){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent =  Intent.createChooser(sendIntent, title)
        appCompatActivity.startActivity(shareIntent)
    }
}