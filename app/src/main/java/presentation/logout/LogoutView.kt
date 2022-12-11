package presentation.logout

interface LogoutView {
    fun onLogoutSuccess(success :String)
    fun onLogoutFailed(error: String?)
}