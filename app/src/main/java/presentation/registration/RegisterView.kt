package presentation.registration

import data.repository.entity.User

interface RegisterView {
    fun checkingData()
    fun onRegisterSuccess(user: User)
    fun onRegisterFailed(error: String?)
}