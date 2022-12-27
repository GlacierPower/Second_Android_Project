package application.domain.login

import application.data.AuthRepositoryCallBack
import application.model.User
import com.google.firebase.auth.FirebaseAuth

interface LoginRepository {

    fun loginUser(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth,
    )


}