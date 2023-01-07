package application.presentation.activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import application.presentation.items.ItemsFragment
import application.presentation.login.LoginFragment
import application.untils.NavigationOnFragment.replaceFragment
import com.zhenya_flower.firstlesson_kotlin.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.checkExists()
        viewModel.nav.observe(this) { auth ->
            if (auth.currentUser != null) {
                Log.i(TAG, getString(R.string.log_success))
                replaceFragment(
                    supportFragmentManager,
                    ItemsFragment(),
                    false
                )
            } else {
                Log.i(TAG, getString(R.string.log_fail))
                replaceFragment(
                    supportFragmentManager,
                    LoginFragment(),
                    true
                )
            }
        }

    }
}