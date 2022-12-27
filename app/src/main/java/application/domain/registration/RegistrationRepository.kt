package application.domain.registration

import application.data.AuthRepositoryCallBack
import application.model.User
import com.google.firebase.auth.FirebaseAuth

interface RegistrationRepository {

    fun registerUser(
        user: User,
        auth: FirebaseAuth,
        listener :AuthRepositoryCallBack
    )

}