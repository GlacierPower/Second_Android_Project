package data.repository.repository

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import data.repository.AuthRepositoryCallBack
import data.repository.entity.User
import presentation.logout.MainFragment

class LoginRepository(private val auth: FirebaseAuth) {

    fun doLogin(user: User, listener: AuthRepositoryCallBack) {
        auth
            .signInWithEmailAndPassword(user.email as String, user.password as String)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userID = task.result?.user?.uid

                } else {
                    listener.fail(task.exception?.message)
                }
            }
    }
}


