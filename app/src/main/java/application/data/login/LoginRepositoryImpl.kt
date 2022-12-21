package application.data.login

import android.util.Log
import application.data.AuthRepositoryCallBack
import application.domain.login.LoginRepository
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {

    override fun loginUser(
        user: User,
        listener: AuthRepositoryCallBack,
        auth: FirebaseAuth,
    ) {
        val emailVerify = FirebaseAuth.getInstance().currentUser
        if (emailVerify?.isEmailVerified == false) {
            Log.w("Check", "Email verified")
            auth
                .signInWithEmailAndPassword(user.email as String, user.password as String)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userID = task.result?.user?.uid

                    } else {
                        listener.fail(task.exception?.message)
                    }
                }
        } else {
            Log.w("Error", "Email didn't verify")
        }
    }


}


