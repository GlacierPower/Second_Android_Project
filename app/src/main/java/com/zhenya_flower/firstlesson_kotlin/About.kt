package com.zhenya_flower.firstlesson_kotlin

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

// i'LL fix it
class About : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("ABOUT")
                .setMessage("Version 1.0" +
                        "By Glacier Power")
                .setPositiveButton("Cancel") {
                        dialog, _ ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    companion object {
        fun startAbout(context: Context) {
            val intent = Intent(context, About::class.java)
            context.startActivity(intent)
        }
    }
}
