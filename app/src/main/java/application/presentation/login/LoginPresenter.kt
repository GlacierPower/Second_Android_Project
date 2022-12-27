package application.presentation.login

import application.data.AuthRepositoryCallBack
import application.domain.login.LoginInteractor
import application.model.User
import application.untils.AppConstants.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val loginInteractor: LoginInteractor,
) {
    lateinit var loginView: LoginView

    fun setView(loginFragment: LoginFragment) {
        loginView = loginFragment
    }

    fun doLogin(user: User) {
        if (user.email.isNullOrEmpty()) {
            loginView.onEmailEmpty()
            return
        }
        if (user.email?.isEmailValid() == false) {
            loginView.onEmailInvalid()
            return
        }

        if (user.password?.isEmpty() == true) {
            loginView.onPasswordEmpty()
            return
        }

        loginInteractor.loginUser(user, object : AuthRepositoryCallBack {
            override fun success(user: User) {
                loginView.onLoginSuccess(user)
            }

            override fun fail(error: String?) {
                loginView.onLoginFailed(error)

            }

        }, FirebaseAuth.getInstance())



    }

}