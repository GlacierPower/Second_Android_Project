package application.presentation.forgot_pass

import android.util.Log
import application.data.AuthRepositoryCallBack
import application.domain.forgot_pass.ForgotPassInteractor
import application.model.User
import application.untils.AppConstants.EXCEPTION
import application.untils.AppConstants.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import javax.inject.Inject


class ForgotPassPresenter @Inject constructor(
    private val forgotPassInteractor: ForgotPassInteractor
) {

    lateinit var forgotPassView: ForgotPassView

    fun setView(forgotPassFragment: ForgotPassFragment) {
        forgotPassView = forgotPassFragment
    }

    fun doReset(user: User) {

        val coroutinesExceptionHandler = CoroutineExceptionHandler { _, exceprion ->
            Log.w(EXCEPTION, exceprion.toString())
        }

        CoroutineScope(Dispatchers.Main + coroutinesExceptionHandler).launch {
            try {
                if (validation(user)) {
                    forgotPassInteractor.forgotPassword(user, object :
                        AuthRepositoryCallBack {
                        override fun success(user: User) {
                            forgotPassView.onResetSuccess(user)
                        }

                        override fun fail(error: String?) {
                            forgotPassView.onResetFailed()
                        }

                    }, FirebaseAuth.getInstance())
                }
            } catch (e: Exception) {
                forgotPassView.onResetFailed()
            }
            joinAll()
            cancel()
        }
    }

    private fun validation(user: User): Boolean {
        var isValid = true
        if (user.email.isNullOrEmpty()) {
            isValid = false
            forgotPassView.onEmailEmpty()

        }
        if (user.email?.isEmailValid() == false) {
            isValid = false
            forgotPassView.onEmailInvalid()

        }
        return isValid
    }
}

