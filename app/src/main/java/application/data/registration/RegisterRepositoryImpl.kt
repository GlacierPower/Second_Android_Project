package application.data.registration

import application.data.AuthRepositoryCallBack
import application.domain.registration.RegistrationRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(

) : RegistrationRepository {

    override fun registerUser(user: User, auth: FirebaseAuth, listener: AuthRepositoryCallBack) {
        auth
            .createUserWithEmailAndPassword(user.email as String, user.password as String)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userID = task.result?.user?.uid
                    val newUser = user.copy(
                        userID = userID,
                        password = null,
                        confirmPassword = null
                    )
                    listener.success(newUser)
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                            } else {

                            }
                            auth.signOut()
                        }
                }
            }

    }

}


