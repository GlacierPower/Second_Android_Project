package application.domain.forgot_pass

import application.data.AuthRepositoryCallBack
import application.domain.login.LoginRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ForgotPassInteractor@Inject constructor(
    private val forgotPassRepository: ForgotPassRepository
    ) {
    fun forgotPassword(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth
    ) {
        forgotPassRepository.forgotPass(user, listener, auth)
    }
}