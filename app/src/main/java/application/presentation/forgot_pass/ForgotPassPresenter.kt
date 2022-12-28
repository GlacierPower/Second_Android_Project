package application.presentation.forgot_pass

import application.data.AuthRepositoryCallBack
import application.domain.forgot_pass.ForgotPassInteractor
import application.model.User
import application.untils.AppConstants.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class ForgotPassPresenter @Inject constructor(
    private val forgotPassInteractor: ForgotPassInteractor
) {

    lateinit var forgotPassView: ForgotPassView

    fun setView(forgotPassFragment: ForgotPassFragment) {
        forgotPassView = forgotPassFragment
    }

    fun doReset(user: User) {
        if (user.email.isNullOrEmpty()) {
            forgotPassView.onEmailEmpty()
            return
        }
        if (user.email?.isEmailValid() == false) {
            forgotPassView.onEmailInvalid()
            return
        }
        forgotPassInteractor.forgotPassword(user, object :
            AuthRepositoryCallBack {
            override fun success(user: User) {
                forgotPassView.onResetSuccess(user)
            }

            override fun fail(error: String?) {
                forgotPassView.onResetFailed(error)
            }

        }, FirebaseAuth.getInstance())
    }
}