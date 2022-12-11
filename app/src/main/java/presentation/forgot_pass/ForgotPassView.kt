package presentation.forgot_pass

import data.repository.entity.User

interface ForgotPassView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onResetSuccess(user: User)
    fun onProgress(visibility: Int)
    fun onResetFailed(error: String?)
}