package application.presentation.registration

import android.view.View
import application.data.AuthRepositoryCallBack
import application.domain.registration.RegistrationInteractor
import application.model.User
import application.presentation.login.LoginFragment
import application.untils.AppConstants.isEmailValid
import application.untils.NavigationOnFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class RegisterPresenter @Inject constructor(
    private val registrationInteractor: RegistrationInteractor

) {

    private lateinit var registerView: RegisterView

    fun setView(registrationFragment: RegistrationFragment) {
        registerView = registrationFragment
    }

    fun doRegister(user: User) {

        if (user.username?.isEmpty() == true) {
            registerView.onUserNameEmpty()
            return
        }
        if (user.email?.isEmpty() == true) {
            registerView.onEmailEmpty()
            return
        }
        if (user.email?.isEmailValid() == false) {
            registerView.onEmailInvalid()
            return
        }
        if (user.password?.isEmpty() == true) {
            registerView.onPasswordEmpty()
            return
        }
        if ((user.password?.length ?: 0) < 6) {
            registerView.onPasswordToShort()
            return
        }

        if (user.confirmPassword?.isEmpty() == true) {
            registerView.onConfirmPasswordEmpty()
            return
        }

        if (user.confirmPassword != user.password) {
            registerView.onConfirmPasswordNotMatch()
            return
        }

        registerView.onRegisterStart()
        registerView.onProgress(View.GONE)

        registrationInteractor.registerUser(user, object :
            AuthRepositoryCallBack {
            override fun success(user: User) {
                registerView.onProgress(View.VISIBLE)
            }

            override fun fail(error: String?) {
                registerView.onRegisterFailed(error)
                registerView.onProgress(View.VISIBLE)
            }
        }, FirebaseAuth.getInstance())




    }


}
