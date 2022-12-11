package data.repository.repository

import com.google.firebase.auth.FirebaseAuth
import data.repository.AuthRepositoryCallBack
import data.repository.entity.User

class RegisterRepository(private val auth: FirebaseAuth) {
    fun doRegister(user: User, listener: AuthRepositoryCallBack) {
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
}


