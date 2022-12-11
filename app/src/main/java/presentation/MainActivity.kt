package presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhenya_flower.firstlesson_kotlin.R
import presentation.login.LoginFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activityContainer, LoginFragment())
        fragmentTransaction.commit()

    }
}