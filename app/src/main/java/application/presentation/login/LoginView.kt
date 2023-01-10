package application.presentation.login

import application.model.User
import com.google.firebase.FirebaseError

interface LoginView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onPasswordToShort()
    fun onProgress(visibility: Int)
    fun onLoginSuccess(user: User)
    fun onLoginFailed()
}