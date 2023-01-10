package application.presentation.forgot_pass

import application.model.User

interface ForgotPassView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onResetSuccess(user: User)
    fun onResetFailed()
}