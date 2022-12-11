package presentation.logout

import data.repository.repository.LogoutRepository

class LogoutPresenter(private val logoutRepository: LogoutRepository) {
    fun logOut() {
        logoutRepository.logOut()
    }
}