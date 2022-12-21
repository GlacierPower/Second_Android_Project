package application.presentation.logout

import application.domain.logout.LogoutInteractor
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogoutPresenter @Inject constructor(
    private val logoutInteractor: LogoutInteractor
) {
    fun logOut() {
        logoutInteractor.logOut(FirebaseAuth.getInstance())
    }
}