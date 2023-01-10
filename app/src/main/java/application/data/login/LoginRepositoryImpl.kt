package application.data.login

import application.data.AuthRepositoryCallBack
import application.domain.login.LoginRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {

    override suspend fun loginUser(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth
    ) {

        auth
            .signInWithEmailAndPassword(user.email as String, user.password as String)
            .addOnCompleteListener {task->
                if (task.isSuccessful) {
                    listener.success(user)
                } else {
                    listener.fail(task.exception?.message)
                }
            }
    }
}


