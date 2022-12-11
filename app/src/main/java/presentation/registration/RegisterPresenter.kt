package presentation.registration

import android.util.Patterns
import android.view.View

import com.google.android.material.snackbar.Snackbar

import data.repository.AuthRepositoryCallBack
import data.repository.entity.User
import data.repository.repository.RegisterRepository


class RegisterPresenter(
    private val registerRepository: RegisterRepository,
) {
    fun View.showsnackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }

    private lateinit var view: RegisterView
    fun doRegister(user: User) {

        if (user.email!!.isBlank() || user.password!!.isBlank() || user.confirmPassword!!.isBlank()) {
            return
        }
        if (user.password != user.confirmPassword) {
            return
        } else {
            registerRepository.doRegister(user, object : AuthRepositoryCallBack {
                override fun success(user: User) {

                }

                override fun fail(error: String?) {
                    view.onRegisterFailed(error)
                }

            })

        }
    }

    private fun String.isEmailValid() =
        this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
