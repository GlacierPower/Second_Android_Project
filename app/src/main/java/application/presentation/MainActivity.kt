package application.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import application.presentation.login.LoginFragment
import application.presentation.logout.MainFragment
import application.untils.NavigationOnFragment.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityView {
    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityPresenter.setView(this)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            replaceFragment(
                supportFragmentManager,
                MainFragment(),
                false
            )
        } else
            replaceFragment(
                supportFragmentManager,
                LoginFragment(),
                true
            )


    }

    override fun userLogged(auth: FirebaseAuth) {

    }


}