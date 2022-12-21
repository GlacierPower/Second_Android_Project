package application.domain.forgot_pass

import application.data.AuthRepositoryCallBack
import application.model.User
import com.google.firebase.auth.FirebaseAuth

interface ForgotPassRepository {
    fun forgotPass(
        user: User,
        listener : AuthRepositoryCallBack,
        auth: FirebaseAuth,
    )
}