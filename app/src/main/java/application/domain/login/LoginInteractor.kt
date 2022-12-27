package application.domain.login

import application.data.AuthRepositoryCallBack
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val loginRepository: LoginRepository,

    ) {
    fun loginUser(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth
    ) {
        loginRepository.loginUser(user, listener, auth)
    }

}