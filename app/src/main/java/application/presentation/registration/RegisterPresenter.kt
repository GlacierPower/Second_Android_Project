package application.presentation.registration

import android.util.Log
import android.view.View
import application.data.AuthRepositoryCallBack
import application.domain.registration.RegistrationInteractor
import application.model.User
import application.untils.AppConstants.EXCEPTION
import application.untils.AppConstants.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import javax.inject.Inject


class RegisterPresenter @Inject constructor(
    private val registrationInteractor: RegistrationInteractor

) {

    private lateinit var registerView: RegisterView

    fun setView(registrationFragment: RegistrationFragment) {
        registerView = registrationFragment
    }

    fun doRegister(user: User) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exceprion ->
            Log.w(EXCEPTION, exceprion.toString())
        }

        CoroutineScope(Dispatchers.Main + coroutineExceptionHandler).launch {
            try {
                if (validation(user)) {
                    registrationInteractor.registerUser(user, object :
                        AuthRepositoryCallBack {
                        override fun success(user: User) {
                            registerView.onRegisterStart()
                            registerView.onProgress(View.VISIBLE)
                        }

                        override fun fail(error: String?) {
                            registerView.onRegisterFailed()
                            registerView.onProgress(View.VISIBLE)
                        }
                    }, FirebaseAuth.getInstance())
                }
            } catch (e: Exception) {
                registerView.onRegisterFailed()
            }
            joinAll()
            cancel()
        }

    }

    private fun validation(user: User): Boolean {
        var isValid = true

        if (user.username?.isEmpty() == true) {
            isValid = false
            registerView.onUserNameEmpty()
        }
        if (user.email?.isEmpty() == true) {
            isValid = false
            registerView.onEmailEmpty()
        }
        if (user.email?.isEmailValid() == false) {
            isValid = false
            registerView.onEmailInvalid()
        }
        if (user.password?.isEmpty() == true) {
            isValid = false
            registerView.onPasswordEmpty()
        }
        if ((user.password?.length ?: 0) < 6) {
            isValid = false
            registerView.onPasswordToShort()
        }

        if (user.confirmPassword?.isEmpty() == true) {
            isValid = false
            registerView.onConfirmPasswordEmpty()
        }

        if (user.confirmPassword != user.password) {
            isValid = false
            registerView.onConfirmPasswordNotMatch()
        }

        return isValid
    }

}
