package application.data.registration

import application.data.AuthRepositoryCallBack
import application.domain.registration.RegistrationRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor() : RegistrationRepository {

    override fun registerUser(user: User, listener: AuthRepositoryCallBack, auth: FirebaseAuth) {
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
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                            }
                        }
                } else {
                    listener.fail(task.exception?.message)
                }
            }
    }

    override fun verifyEmail(auth: FirebaseAuth) {
        val user = auth.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener { task->
                if (task.isSuccessful){

                }else{

                }
            }
    }
}


