package com.app.buscame.features.redirectToPage

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

class RedirectToPage(val activity: Fragment){

    fun redirect(url : String = "https://www.google.com"){
        var finalUrl = if(!url.contains("https://")) "https://$url" else url
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(finalUrl)
        }
        activity.startActivity(intent)
    }

}