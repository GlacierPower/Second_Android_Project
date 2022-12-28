package application.data.forgot_pass

import application.data.AuthRepositoryCallBack
import application.domain.forgot_pass.ForgotPassRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ForgotPasswordRepositoryImpl @Inject constructor() : ForgotPassRepository {
    override fun forgotPass(user: User, listener: AuthRepositoryCallBack, auth: FirebaseAuth) {
        auth
            .sendPasswordResetEmail(user.email as String)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listener.success(user)
                } else
                    listener.fail(task.exception?.message)
            }
    }
}
