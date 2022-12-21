package application.presentation.login

import androidx.fragment.app.FragmentManager
import application.data.AuthRepositoryCallBack
import application.domain.login.LoginInteractor
import application.model.User
import application.presentation.logout.MainFragment
import application.untils.NavigationOnFragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val loginInteractor: LoginInteractor,
    ) {
    private var view: LoginView? = null
    fun doLogin(user: User) {
        if (user.email?.isNullOrEmpty() == true) {
            view?.onEmailEmpty()
            return
        }


        if (user.password?.isEmpty() == true) {
            view?.onPasswordEmpty()
            return
        }
        loginInteractor.loginUser(user, object : AuthRepositoryCallBack {
            override fun success(user: User) {
            }

            override fun fail(error: String?) {
                view?.onLoginFailed(error)

            }

        }, FirebaseAuth.getInstance())

//        NavigationOnFragment.replaceFragment(
//            FragmentManager(),
//            MainFragment(),
//            false
//        )
    }
}