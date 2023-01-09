package application.domain.logout

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainFragmentInteractor @Inject constructor(
    private val mainFragmentRepository: MainFragmentRepository) {
    fun logOut(auth: FirebaseAuth){
        mainFragmentRepository.logOut(auth)
    }
}