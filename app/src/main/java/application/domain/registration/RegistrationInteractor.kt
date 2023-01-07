package application.domain.registration

import application.data.AuthRepositoryCallBack
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegistrationInteractor @Inject constructor(
    private val registrationRepository: RegistrationRepository
) {
    fun registerUser(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth
    ) {
        registrationRepository.registerUser(user,listener,auth)
    }

}