package application.presentation.login

import android.util.Log
import android.view.View
import application.data.AuthRepositoryCallBack
import application.domain.login.LoginInteractor
import application.model.User
import application.untils.AppConstants.EXCEPTION
import application.untils.AppConstants.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val loginInteractor: LoginInteractor,
) {
    lateinit var loginView: LoginView

    fun setView(loginFragment: LoginFragment) {
        loginView = loginFragment
    }

    fun doLogin(user: User) {

        val coroutinesExceptionHandler = CoroutineExceptionHandler { _, exceprion ->
            Log.w(EXCEPTION, exceprion.toString())
        }
        CoroutineScope(Dispatchers.Main + coroutinesExceptionHandler).launch {
            try {
                if (validation(user)) {
                    loginInteractor.loginUser(user, object : AuthRepositoryCallBack {
                        override fun success(user: User) {
                            loginView.onLoginSuccess(user)
                            loginView.onProgress(View.VISIBLE)
                        }

                        override fun fail(error: String?) {
                            loginView.onLoginFailed()
                            loginView.onProgress(View.VISIBLE)

                        }

                    }, FirebaseAuth.getInstance())
                }
            } catch (e: Exception) {
                loginView.onLoginFailed()
            }
            joinAll()
            cancel()
        }

    }

    private fun validation(user: User): Boolean {
        var isValid = true

        if (user.email.isNullOrEmpty()) {
            isValid = false
            loginView.onEmailEmpty()

        }
        if (user.email?.isEmailValid() == false) {
            isValid = false
            loginView.onEmailInvalid()
        }

        if (user.password?.isEmpty() == true) {
            isValid = false
            loginView.onPasswordEmpty()
        }
        if ((user.password?.length ?: 0) < 6) {
            isValid = false
            loginView.onPasswordToShort()
        }
        return isValid
    }

}