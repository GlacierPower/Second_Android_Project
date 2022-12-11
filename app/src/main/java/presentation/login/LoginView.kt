package presentation.login

import data.repository.entity.User

interface LoginView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onLoginSuccess(user: User)
    fun onLoginFailed(error: String?)
}