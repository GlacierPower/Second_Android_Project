package com.zhenya_flower.firstlesson_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class Theme : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.theme, container)
        val firstTheme = view.findViewById<RadioButton>(R.id.firstTheme)
        val secondTheme = view.findViewById<RadioButton>(R.id.secondTheme)
        firstTheme.setOnClickListener {
            if (firstTheme.isChecked) {
                secondTheme.isChecked = false
            } else {
                firstTheme.isChecked = true
            }
        }
        secondTheme.setOnClickListener{
            if (secondTheme.isChecked){
                firstTheme.isChecked = false
            }else{
                secondTheme.isChecked=true
            }
        }
        return (inflater.inflate(R.layout.theme, container))
    }

    override fun onStart() {
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    companion object {
        private val myDialogFragment = Theme()
        fun startTheme(manager: FragmentManager) {
            myDialogFragment.show(manager, "Theme")
        }
    }
}
