package application.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import application.presentation.login.LoginFragment
import com.zhenya_flower.firstlesson_kotlin.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activityContainer, LoginFragment())
        fragmentTransaction.commit()

    }
}