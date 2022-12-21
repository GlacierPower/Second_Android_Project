package application.presentation.forgot_pass

import android.view.View
import application.data.AuthRepositoryCallBack
import application.data.forgot_pass.ForgotPasswordRepositoryImpl
import application.domain.forgot_pass.ForgotPassInteractor
import application.domain.login.LoginInteractor
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class ForgotPassPresenter@Inject constructor(
    private val forgotPassInteractor: ForgotPassInteractor
)  {
    private var view: ForgotPassView? = null
    fun doReset(user: User) {
        if (user.email?.isEmpty() == true) {
            view?.onEmailEmpty()
            return
        }
        forgotPassInteractor.forgotPassword(user, object :
            AuthRepositoryCallBack {
            override fun success(user: User) {
                view?.onResetSuccess(user)
                view?.onProgress(View.VISIBLE)
            }

            override fun fail(error: String?) {
                view?.onResetFailed(error)
                view?.onProgress(View.VISIBLE)
            }

        }, FirebaseAuth.getInstance())
    }
}