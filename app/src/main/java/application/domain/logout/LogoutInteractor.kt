package application.domain.logout

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogoutInteractor @Inject constructor(
    private val logoutRepository: LogoutRepository) {
    fun logOut(auth: FirebaseAuth){
        logoutRepository.logOut(auth)
    }
}