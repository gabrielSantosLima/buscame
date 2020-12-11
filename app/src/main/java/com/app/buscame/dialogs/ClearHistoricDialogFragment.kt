package com.app.buscame.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.app.buscame.R
import com.app.buscame.features.historic.HistoricManagerJson
import com.app.buscame.features.historic.IHistoricManager

class ClearHistoricDialogFragment(val applicationContext: Context) : DialogFragment(){

    private lateinit var historicManagerJson: IHistoricManager

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        historicManagerJson = HistoricManagerJson(applicationContext.filesDir.path)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.cleanHistoricMessage)
                .setPositiveButton(
                    R.string.cleanHistoricConfirm
                ) { _, _ -> historicManagerJson.removeAll() }
                .setNegativeButton(
                    R.string.cleanHistoricCancel
                ) { dialog, id -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}