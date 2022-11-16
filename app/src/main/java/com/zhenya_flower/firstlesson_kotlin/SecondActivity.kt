package com.zhenya_flower.firstlesson_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.title = intent.getStringExtra(TITLE_KEY)?:
        (getString(R.string.defaultTitle))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val firstTextView = findViewById<TextView>(R.id.firstMessage)
        val secondTextView = findViewById<TextView>(R.id.secondMessage)
        val data = intent.getStringExtra(TEXT_KEY)
        val secData = intent.getStringExtra(SEC_TEXT_KEY)
        firstTextView.text = data
        secondTextView.text = secData

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TEXT_KEY = "1"
        private const val SEC_TEXT_KEY = "2"
        private const val TITLE_KEY = "3"
        private const val COLOR_KEY = "4"

        fun startSecond(context: Context, firstMessage: String, secondMessage: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(TEXT_KEY, firstMessage)
            intent.putExtra(SEC_TEXT_KEY, secondMessage)
            context.startActivity(intent)
        }

        fun changeTitle(context: Context, title: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(TITLE_KEY, title)
            context.startActivity(intent)
        }
        fun changeColor(context: Context,color:Int){
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }
}

