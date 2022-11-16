package com.zhenya_flower.firstlesson_kotlin



import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.zhenya_flower.firstlesson_kotlin.About.Companion.startAbout
import com.zhenya_flower.firstlesson_kotlin.SecondActivity.Companion.changeColor
import com.zhenya_flower.firstlesson_kotlin.SecondActivity.Companion.changeTitle
import com.zhenya_flower.firstlesson_kotlin.SecondActivity.Companion.startSecond



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goToSecond -> {
                startSecond(this, getString(R.string.secondMessage), getString(R.string.message))
            }
            R.id.changeTitle -> {
                changeTitle(this, getString(R.string.title))
            }
            R.id.changeBackground -> {
                changeColor(this, getColor(R.color.black))
            }
            R.id.about -> {
                startAbout(supportFragmentManager)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}
