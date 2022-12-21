package application.presentation.login

import application.model.User

interface LoginView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onLoginSuccess(user: User)
    fun onLoginFailed(error: String?)
}