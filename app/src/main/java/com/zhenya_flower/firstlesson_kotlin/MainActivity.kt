package com.zhenya_flower.firstlesson_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGoTo = findViewById<Button>(R.id.goToSecond)
        btnGoTo.setOnClickListener {
            startActivity(Intent("com.zhenya_flower.firstlesson_kotlin.OPEN_SECOND_ACTIVITY"))
        }
    }
}