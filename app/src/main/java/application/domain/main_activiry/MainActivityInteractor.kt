package application.domain.main_activiry

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainActivityInteractor @Inject constructor(
    private val mainActivityRepository: MainActivityRepository
) {
    fun userExists(): FirebaseAuth {
        return mainActivityRepository.userLoggedIn()
    }
}