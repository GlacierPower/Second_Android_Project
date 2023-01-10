package application.domain.main_fragment

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainFragmentInteractor @Inject constructor(
    private val mainFragmentRepository: MainFragmentRepository) {
   suspend fun logOut(auth: FirebaseAuth){
        mainFragmentRepository.logOut(auth)
    }
}