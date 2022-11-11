package com.zhenya_flower.firstlesson_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val btnGoTo = findViewById<Button>(R.id.goToFourth)
        btnGoTo.setOnClickListener {
            startActivity(
                Intent(this,FourthActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}