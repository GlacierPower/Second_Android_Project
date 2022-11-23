package com.zhenya_flower.firstlesson_kotlin

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.zhenya_flower.firstlesson_kotlin.Theme.Companion.startTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_face = findViewById<ImageView>(R.id.facebookView)
        val btn_google = findViewById<ImageView>(R.id.googleView)
        val  boeing = PlaneBuilder
            .setEngine(4)
            .setPassengers(550)
            .setWeapon(false)
            .setType("civil")
            .setSpeed(988.1)
            .buildPlane()
        val stealth = PlaneBuilder
            .setEngine(2)
            .setPassengers(5)
            .setWeapon(true)
            .setType("military")
            .setSpeed(6125.2)
            .buildPlane()
        btn_google.setOnClickListener {
            Log.w("Plane builder","@${boeing.howMuchEngine()} ${boeing.howMuchPass()} " +
                    "${boeing.hasWeapon()} ${boeing.planeType()} ${boeing.plainSpeed()}")
        }
        btn_face.setOnClickListener {
            Log.w("Plane builder","@${stealth.howMuchEngine()} ${stealth.howMuchPass()} " +
                    "${stealth.hasWeapon()} ${stealth.planeType()} ${stealth.plainSpeed()}")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_act_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.changeColor -> startTheme(supportFragmentManager)
        }
        return super.onOptionsItemSelected(item)
    }
}