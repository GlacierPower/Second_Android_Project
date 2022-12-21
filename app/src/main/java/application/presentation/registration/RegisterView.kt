package application.presentation.registration

import application.model.User


interface RegisterView {
    fun checkingData()
    fun onRegisterSuccess(user: User)
    fun onRegisterFailed(error: String?)
}