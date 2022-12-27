package application.data.login

import application.data.AuthRepositoryCallBack
import application.domain.login.LoginRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {

    override fun loginUser(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth
    ) {

        auth
            .signInWithEmailAndPassword(user.email as String, user.password as String)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val userId = it.result?.user?.uid
                    listener.success(user)
                } else {
                    listener.fail(it.exception?.message)
                }
            }
    }
}


