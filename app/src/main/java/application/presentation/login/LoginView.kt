package application.presentation.login

import application.model.User
import application.presentation.AuthenticationPageListener
import com.google.firebase.auth.FirebaseAuth

interface LoginView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onLoginSuccess(user:User)
    fun onLoginFailed(error: String?)
    fun userNotFound()
}