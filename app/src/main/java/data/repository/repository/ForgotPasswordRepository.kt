package data.repository.repository

import com.google.firebase.auth.FirebaseAuth
import data.repository.AuthRepositoryCallBack
import data.repository.entity.User

class ForgotPasswordRepository(private val auth: FirebaseAuth) {
    fun forgotPass(user: User, listener: AuthRepositoryCallBack) {
        auth
            .sendPasswordResetEmail(user.email as String)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                }
            }
    }
}
