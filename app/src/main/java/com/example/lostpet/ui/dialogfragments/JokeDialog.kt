package com.example.lostpet.ui.dialogfragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.lostpet.R

class JokeDialog : DialogFragment(), DialogInterface.OnClickListener {
    val LOG_TAG = "myLogs"
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val adb: AlertDialog.Builder = AlertDialog.Builder(activity)
            .setTitle("Кнопка редактирования")
            .setPositiveButton(R.string.yes, this)
            .setNegativeButton(R.string.no, this)
            .setMessage(R.string.editMark)
        return adb.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        var i = 0
        when (which) {
            Dialog.BUTTON_POSITIVE ->{
                val intent = Intent(
                    Intent.ACTION_DIAL, Uri.parse("tel:" + "+79514462853"))
                startActivity(intent)
            }
            Dialog.BUTTON_NEGATIVE -> i = R.string.no
        }
        if (i > 0) Log.d(LOG_TAG, "Dialog 2: " + resources.getString(i))
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(LOG_TAG, "Dialog 2: onDismiss")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Log.d(LOG_TAG, "Dialog 2: onCancel")
    }
}