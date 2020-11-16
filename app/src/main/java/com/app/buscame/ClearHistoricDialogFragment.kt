package com.app.buscame

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ClearHistoricDialogFragment : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.cleanHistoricMessage)
                .setPositiveButton(R.string.cleanHistoricConfirm,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                .setNegativeButton(R.string.cleanHistoricCancel,
                    DialogInterface.OnClickListener { dialog, id ->

                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}