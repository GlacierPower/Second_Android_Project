package com.zhenya_flower.firstlesson_kotlin



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


// i'LL fix it
class About : DialogFragment() {
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return (inflater.inflate(R.layout.about, container, false))
    }

    override fun onStart() {

        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    companion object{
        private val myDialogFragment = About()
        fun startAbout(manager: FragmentManager) {
            myDialogFragment.show(manager, "myDialog")
            val btn = myDialogFragment.dialog?.findViewById<Button>(R.id.cancel)
                btn?.setOnClickListener {
                myDialogFragment.dismiss()
            }
        }
    }

}



