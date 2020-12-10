package com.app.buscame.features.importImage

import android.content.Intent
import androidx.fragment.app.Fragment

class ImportImage(private val activity: Fragment) : IImportImage{

    companion object{
        val REQUEST_CODE_IMPORT_IMAGE = 1
    }

    fun import() {
        val intent = Intent().apply {
            action = Intent.ACTION_PICK
            type = "image/**"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
        }
        activity.startActivityForResult(intent, REQUEST_CODE_IMPORT_IMAGE)
    }
}