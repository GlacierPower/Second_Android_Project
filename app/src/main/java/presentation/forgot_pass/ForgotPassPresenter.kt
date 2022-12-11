package presentation.forgot_pass

import android.view.View
import data.repository.AuthRepositoryCallBack
import data.repository.entity.User
import data.repository.repository.ForgotPasswordRepository


class ForgotPassPresenter(private val forgotPasswordRepository: ForgotPasswordRepository) {
    private var view: ForgotPassView? = null
    fun doReset(user: User) {
        if (user.email?.isEmpty() == true) {
            view?.onEmailEmpty()
            return
        }
        forgotPasswordRepository.forgotPass(user, object : AuthRepositoryCallBack {
            override fun success(user: User) {
                view?.onResetSuccess(user)
                view?.onProgress(View.VISIBLE)
            }

            override fun fail(error: String?) {
                view?.onResetFailed(error)
                view?.onProgress(View.VISIBLE)
            }

        })
    }
}